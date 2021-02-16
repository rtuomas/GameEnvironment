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
	//Players bet (starting with 1.00)
	private double bet;
	private Deck deck;
	private Hand hand;
	private Player player;
	
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
				// dealHand -> hand to controller -> hand to view
				// update credits -> controller -> view
				// get score -> controller -> view
				// database stuff
				// thread ends
		dealCards();
		updateCredits();
		setScore();
	}
	
	@Override
	public void dealCards () {
		this.hand = new Hand(deck);
		controller.showCards(this.hand.getHand());
	}
	
	@Override
	public void setBet(double bet) {
		this.bet = bet;
	}
	
	@Override
	public void setScore () {
		controller.setScore(this.hand.getScore().name());
	}
	
	@Override
	public void setPlayer (String name) {
		Player [] players = this.dao.readPlayers();
		String [] names = name.split(" ");
		for(Player p : players) {
			if(p.getFirstName().equals(names[0]) && p.getLastName().equals(names[1])) {
				this.player = p;
			}
		}
	}
	
	
	@Override
	public double getCredits () {
		return (double)this.player.getCredits();
	}
	
	@Override
	public void updateCredits () {
		if(this.hand.getScore() != HandValue.NO_WIN) {
			double win = this.hand.getScore().getMultiplier() * this.bet;
			this.player.setCredits(this.player.getCredits() + (int)win);
			controller.setCredits();
		}
		this.player.setCredits(this.player.getCredits() - (int)this.bet);
		controller.setCredits();
	}
}
