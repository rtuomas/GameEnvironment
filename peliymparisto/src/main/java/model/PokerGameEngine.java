package model;

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
	}
	
}
