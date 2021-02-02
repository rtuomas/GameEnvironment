package model;

import java.util.Arrays;

import controller.ControllerIF;

/**
 * The game engine for the poker game. It connects all the different classes needed to run the poker game.
 * @author ---
 * @version 1.0 26.01.2021
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
		//game functionality when running through GUI (not CLI)
		deck = new Deck ();
		deck.shuffle();
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
