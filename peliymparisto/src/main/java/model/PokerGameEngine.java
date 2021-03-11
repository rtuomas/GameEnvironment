package model;

import java.util.ArrayList;
import java.util.Arrays;

import controller.ControllerIF;

/**
 * The game engine for the poker game. It connects all the different classes needed to run the poker game.
 * @author ---
 * @version 1.2 18.02.2021
 */
public class PokerGameEngine extends Thread implements ModelIF {
	
	/** Interface with a connection to GUI */
	private ControllerIF controller;
	/** Interface with a connection to DAO */
	private DAOIF dao;
	private Deck deck;
	private Hand hand;
	private ArrayList<Integer> indexes;
	private static Double[] betTable = {0.1, 0.2, 0.4, 0.5, 0.6, 0.8, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 10.0};
	//Players bet (starting with 1.00)
	private static double bet = betTable[6];
	private static double initialCredits = 100;

	//Player variables
	private Player player1;
	private Player player2;
	//Game variables
	private String gameType = "poker";
	
	/**
	 * Constructor for the poker game engine.
	 * Controller and DAO are not needed for CLI to operate!
	 * @param controller is the class which started running the poker game
	 */
	public PokerGameEngine(ControllerIF controller) {
		this.controller = controller;
		//this.stats = new Statistics();
	}
	
	/**
	 * This method uses Threads to run multiple events in the GUI simultaneously
	 */
	public void run() {
		//game functionality
		deck = new Deck ();
		deck.shuffle();
				// dealHand -> hand to controller -> hand to view
				// update credits -> controller -> view
				// get score -> controller -> view
				// database stuff
				// thread ends
		dealCards();
		swapCards();
		endGame(); //called when the game ends
		setScore(); //shows the result in View
	}
	
	/**
	 * Controller calls this method before starting the game
	 */
	public void setUpSinglePlayerGame(Player player) {
		this.player1 = player;
		this.player2 = dao.getPlayer(1000);
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setDatabaseConnection(DAOIF dao) {
		this.dao = dao;
	}
	
	/**
	 * This method is ran at the end of the game to save the played game into database and update credit amounts to players.
	 */
	public void endGame() {
		PlayedGame currentGame;
    Player winner;
    Player loser;
    
		double creditChange = hand.worth() * bet;
		
		if(hand.wins()){
		  winner = player1;
		  loser = player2;
		} else {
		  winner = player2;
		  loser = player1;
		}
		
		winner.alterCredits(creditChange - bet);
		loser.alterCredits(-creditChange - bet);
		
		currentGame = new PlayedGame(player1,player2,gameType,winner,creditChange);
		
		if(player1.getCredits() <= 0){
		  player1.setCredits(initialCredits);
		  controller.notifyCreditReset();
		}

		dao.createPlayedGame(currentGame);
		dao.updatePlayer(player1);
		dao.updatePlayer(player2);
		controller.setCurrentPlayer();
	}
	
	@Override
	public void dealCards () {
		this.hand = new Hand(deck);
		controller.showCards(this.hand.getHand());
	}
	
	/**
	 * Returns current bet amount
	 * @return current bet amount
	 */
	public static double getBet() {
		return bet;
	}
	
	/**
	 * increases current bet amount
	 * @return current bet amount
	 */
	public static double increaseBet() {
		if(bet!=10.0) {
			bet = betTable[Arrays.asList(betTable).indexOf(bet)+1];
		}
		return getBet();
	}
	
	/**
	 * decreases current bet amount
	 * @return current bet amount
	 */
	public static double decreaseBet() {
		if(bet!=0.1) {
			bet = betTable[Arrays.asList(betTable).indexOf(bet)-1];
		}
		return getBet();
	}
	
	@Override
	public void setScore () {
		HandValue score = this.hand.getScore();
		String s = "";
		
		switch(score) {
		case ACE_PAIR:
			s = "Ässä pari";
			break;
		case TWO_PAIRS:
			s = "Kaksi paria";
			break;
		case THREE_OF_A_KIND:
			s = "Kolmoset";
			break;
		case STRAIGHT:
			s = "Suora";
			break;
		case FLUSH:
			s = "Väri";
			break;
		case FULL_HOUSE:
			s = "Täyskäsi";
			break;
		case FOUR_OF_A_KIND:
			s = "Neloset";
			break;
		case STRAIGHT_FLUSH:
			s = "Värisuora";
			break;
		case NO_WIN:
			s = "Ei voittoa";
			break;
		}
		
		if(score.getMultiplier() != 0) {
			controller.setScore(s + ", voitit " + score.getMultiplier() * bet);
		} else {
			controller.setScore(s);
		}
	}
	
	/**
	 * Waits for user interaction until user has decided which cards are going to get swapped.
	 * Swaps cards and returns new hand to controller -> view.
	 */
	public synchronized void swapCards () {
		while(this.indexes == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		controller.showCards(hand.swapCards(this.indexes));
	}
	
	/**
	 * Sets up ArrayList which cards are going to get swapped.
	 */
	public synchronized void setCardsToSwapIndexes(ArrayList<Integer> indexes) {
		this.indexes = new ArrayList<>(indexes);
		notifyAll();
	}
	
	public Player getCurrentPlayer() {
		return this.player1;
	}
}
