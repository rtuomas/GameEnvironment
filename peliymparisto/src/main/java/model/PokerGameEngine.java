package model;

import java.util.Arrays;

import controller.ControllerIF;

/**
 * The game engine for the poker game. It connects all the different classes needed to run the poker game.
 * @author ---
 * @version 1.1 15.02.2021
 */
public class PokerGameEngine extends Thread implements ModelIF {
	
	/** Interface with a connection to GUI */
	private ControllerIF controller;
	/** Interface with a connection to DAO */
	private DAOIF dao;
	//List taken from Veikkaus JokeriPokeri game
	private Double[] betTable = {0.1, 0.2, 0.4, 0.5, 0.6, 0.8, 1.0, 1.5, 2.0, 3.0, 4.0, 5.0, 10.0};
	//Players bet (starting with 1.00)
	private double bet;
	private Deck deck;
	//Player variables
	private Player player1;
	private Player player2;
	//Game variables
	private int winner;
	private int creditChange;
	
	/**
	 * Constructor for the poker game engine.
	 * Controller and DAO are not needed for CLI to operate!
	 * @param controller is the class which started running the poker game
	 */
	public PokerGameEngine(ControllerIF controller) {
		this.controller = controller;
		this.dao = new DAO();
	}
	
	/**
	 * This method uses Threads to run multiple events in the GUI simultaneously
	 */
	public void run() {
		//game functionality
		deck = new Deck ();
		deck.shuffle();
		//endGame() //called when the game ends
	}
	
	/**
	 * Controller calls this method before starting the game
	 */
	public void setUpSinglePlayerGame() {
		this.player2 = dao.getPlayer(1000);
	}
	
	/**
	 * This method is ran at the end of the game to save the played game into database and update credit amounts to players.
	 */
	public void endGame() {
		//specify who is winner before this method
		int crForP1;
		int crForP2;
		int p1ID = this.player1.getId();
		int p2ID = this.player2.getId();
		int cr = this.creditChange; //this could be same as bet but has to be int... fix later
		if (this.winner == this.player1.getId()) {
			crForP1 = this.player1.getCredits() + cr;
			crForP2 = this.player2.getCredits() - cr;
		} else {
			crForP1 = this.player1.getCredits() - cr;
			crForP2 = this.player2.getCredits() + cr;
		}
		PlayedGame currentGame = new PlayedGame(p1ID, p2ID, "poker", this.winner, cr, crForP1, crForP2);
		dao.createPlayedGame(currentGame);
		this.player1.setCredits(crForP1);
		this.player2.setCredits(crForP2);
		dao.updatePlayer(player1);
		dao.updatePlayer(player2);
	}
	
	/**
	 * Increases the bet by certain ammount
	 */
	public void increaseBet() {
		if(this.bet!=10.0) {
			this.bet = betTable[Arrays.asList(betTable).indexOf(this.bet)+1];
		}
	}
	
	/**
	 * Decreases the bet by certain ammount
	 */
	public void decreaseBet() {
		if(this.bet!=0.1) {
			this.bet = betTable[Arrays.asList(betTable).indexOf(this.bet)-1];
		}
	}
	
	@Override
	public Card [] dealCards () {
		Hand hand = new Hand(deck);
		deck.shuffle();
		return hand.getHand();
	}
}
