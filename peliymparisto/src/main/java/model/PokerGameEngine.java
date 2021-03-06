package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import org.codehaus.plexus.util.StringUtils;

import controller.ControllerIF;

/**
 * The game engine for the poker game. It connects all the different classes needed to run the poker game.
 * @author Aki K, Tuomas R, Jere L, Ville R, Tatu N
 * @version 1.3 04.05.2021
 */
public class PokerGameEngine extends Thread implements ModelIF {
	
	/** Interface with a connection to GUI */
	private ControllerIF controller;
	/** Interface with a connection to DAO */
	private DAOIF dao;
	/** Variable for the Deck class */
	private Deck deck;
	/** Variable for the Hand class */
	private Hand hand;
	/** List for card indexes */
	private ArrayList<Integer> indexes;
	/** Bet table of the game */
	private static Double[] betTable = {0.1, 0.2, 0.4, 0.5, 0.6, 0.8, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 10.0};
	/** User's set bet (starting with 1.00) */
	private static double bet = betTable[6];
	/** Not logged in user's initial credits */
	private static double initialCredits = 100;
	
	//Player variables
	/** Player 1 is the current user in a single player game*/
	private Player player1;
	/** Player 2 is the AI in a single player game */
	private Player player2;
	
	//Game variables
	/** gametype of this game as a String */
	private String gameType = "poker";
	/** Boolean object for cashout decision, using object so thread can wait while Boolean null */
	private Boolean cashOut;
	/** String value for gambling */
	private String highOrLow;
	/** Resource factory instance */
	private static final ObservableResourceFactory RESOURCE_FACTORY = ObservableResourceFactory.getInstance();
	/** Resource bundle for languages */
	private ResourceBundle rb;
	/** Decimal format for numbers */
	private DecimalFormat df;
	
	/**
	 * Constructor for the poker game engine.
	 * 
	 * Controller and DAO are not needed for CLI to operate!
	 * @param controller is the class which started running the poker game
	 */
	public PokerGameEngine(ControllerIF controller) {
		this.controller = controller;
		//this.stats = new Statistics();
		 rb = RESOURCE_FACTORY.getResources();
	}
	
	/**
	 * This method uses Threads to run multiple events in the GUI simultaneously
	 */
	public void run() {
		// decimal format from view
		df = controller.getDecimalFormat();
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
		setScore();
		endGame(); //called when the game ends
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
	public void setPlayer1(Player player) {
		this.player1 = player;
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setDatabaseConnection(DAOIF dao) {
		this.dao = dao;
	}
	
	/**	{@inheritDoc} */
	@Override
	public synchronized void setCashout (Boolean decision) {
		this.cashOut = decision;
		notifyAll();
	}
	
	/**
	 * This method is ran at the end of the game to save the played game into database and update credit amounts to players.
	 */
	public synchronized void endGame() {
		// waits for user cashout decision
		if(hand.wins()) {
		while(cashOut == null) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		  }
		}
			
	    PlayedGame currentGame;
	    Player winner;
	    Player loser;
	    
	    // starts gamble feature if user chooses to gamble
	    double creditChange = hand.worth() * bet;
	    if(cashOut != null) {
	    	if(!cashOut) {
	    		creditChange = playHighOrLow(creditChange);
	    	}
	    }
	    
	    if(creditChange > 0){
	      winner = player1;
	      loser = player2;
	    } else {
	      winner = player2;
	      loser = player1;
	    }
	    
	    winner.alterCredits(creditChange - bet);
	    loser.alterCredits(-creditChange - bet);
	    
	    currentGame = new PlayedGame(player1,player2,gameType,winner,creditChange-bet);
	    
	    if(player1.getCredits() <= 0){
		  player1.setCredits(initialCredits);
		  controller.notifyCreditReset();
		}

		dao.createPlayedGame(currentGame);
		dao.updatePlayer(player1);
		dao.updatePlayer(player2);
		controller.setCurrentPlayer();
		controller.setGameState("start");
	}
	
	/**	{@inheritDoc} */
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
	 * Increases current bet amount
	 * @return current bet amount
	 */
	public static double increaseBet() {
		if(bet!=10.0) {
			bet = betTable[Arrays.asList(betTable).indexOf(bet)+1];
		}
		return getBet();
	}
	
	/**
	 * Decreases current bet amount
	 * @return current bet amount
	 */
	public static double decreaseBet() {
		if(bet!=0.1) {
			bet = betTable[Arrays.asList(betTable).indexOf(bet)-1];
		}
		return getBet();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setScore () {
		HandValue score = this.hand.getScore();
		String s = "";
		
		switch(score) {
		case ACE_PAIR:
			s = rb.getString("AcePairWin");
			break;
		case TWO_PAIRS:
			s = rb.getString("TwoPairsWin");
			break;
		case THREE_OF_A_KIND:
			s = rb.getString("ThreeOfKindWin");
			break;
		case STRAIGHT:
			s = rb.getString("StraightWin");
			break;
		case FLUSH:
			s = rb.getString("FlushWin");
			break;
		case FULL_HOUSE:
			s = rb.getString("FullHouseWin");
			break;
		case FOUR_OF_A_KIND:
			s = rb.getString("AcePairWin");
			break;
		case STRAIGHT_FLUSH:
			s = rb.getString("StraightFlushWin");
			break;
		case NO_WIN:
			s = rb.getString("NoWin");
			break;
		}
		
		if(score.getMultiplier() != 0) {
			controller.setScore(s + " - " + rb.getString("YouWon") + " " + df.format(score.getMultiplier() * bet));
			controller.setGameState("win");
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
	
	/**	{@inheritDoc} */
	@Override
	public synchronized void setCardsToSwapIndexes(ArrayList<Integer> indexes) {
		this.indexes = new ArrayList<>(indexes);
		notifyAll();
	}
	
	/**	{@inheritDoc} */
	@Override
	public Player getCurrentPlayer() {
		return this.player1;
	}
	
	/**	{@inheritDoc} */
	@Override
	public synchronized void setHighOrLow (String value) {
		this.highOrLow = value;
		notifyAll();
	}
	
	/**
	 * Gamble feature logic. Gets called from endGame, if users chooses to gamble.
	 * Waits for user guess, then draws new card which decides win or loss.
	 * If gamble is loss, returns 0 to endGame and endGame will continue.
	 * If player wins gamble, method waits for cashout decision, plays new round of gamble if
	 * player decides to gamble again, or sends winning value back to endGame.
	 * @param amount double starting gamble amount
	 * @return double amount of credits left after gambling
	 */
	public synchronized double playHighOrLow (double amount) {
		boolean keepOn = true;
		double winValue = amount;
		
		while(keepOn) {
			while(highOrLow == null && !cashOut) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		if(!cashOut) {
		Card c = deck.nextCard();
		int value = c.getValue();
		
		switch (highOrLow) {
		case "heart":
			if(c.getSuit() == 0) {
				winValue *= 4;
				highOrLow = null;
			} else {
				winValue = 0;
				keepOn = false;
			}
			break;
		case "diamond":
			if(c.getSuit() == 1) {
				winValue *= 4;
				highOrLow = null;
			} else {
				winValue = 0;
				keepOn = false;
			}
			break;
		case "club":
			if(c.getSuit() == 2) {
				winValue *= 4;
				highOrLow = null;
			} else {
				winValue = 0;
				keepOn = false;
			}
			break;
		case "spade":
			if(c.getSuit() == 3) {
				winValue *= 4;
				highOrLow = null;
			} else {
				winValue = 0;
				keepOn = false;
			}
			break;
		case "low":
			if(value < 7 && highOrLow == "low") { 
				winValue *= 2;
				highOrLow = null;
			} else {
				winValue = 0;
				keepOn = false;
			}
			break;
		case "high":
			if(value > 7 && highOrLow == "high") {
				winValue *= 2;
				highOrLow = null;
			} else {
				winValue = 0;
				keepOn = false;
			}
			break;
		}
		
		controller.setHighOrLowCard(c.toString());
		if(winValue > 0) {
			controller.setScore(StringUtils.capitalise(rb.getString("YouWon")) + " " + df.format(winValue));
		} else {
			controller.setScore(rb.getString("NoWin"));
		}
		
		} else {
			keepOn = false;
		}
	}
	
	return winValue;
	}
}
