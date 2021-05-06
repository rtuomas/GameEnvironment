package model;

import java.util.ArrayList;

/**
 * The interface for game engines. The Controller is connected to this interface so it can call different game engines when needed.
 * @author Aki K, Tuomas R, Jere L, Ville R, Tatu N
 * @version 1.1 04.05.2021
 */
public interface ModelIF {
	
	//The common methods for all game engines -->
	
	/**
	 * Deals cards in model and sends cards to controller.
	 */
	public void dealCards();

	/**
	 * Gets model bet.
	 * @param bet double bet size
	 */
	public static double getBet() {
		return 0;
	}
	/**
	 * Sends game score to controller after game.
	 */
	public void setScore();
	
	/**
	 * Sets up indexes which cards are going to be swapped.
	 * @param indexes ArrayList<Integer> card indexes 0-4.
	 */
	public void setCardsToSwapIndexes(ArrayList<Integer> indexes);
	
	public void setUpSinglePlayerGame(Player player); //make non interface
	
	/**
	 * Re setting the player1 while a game is already running
	 * for example upon logging out but continuing with same connection
	 * @param player
	 */
	public void setPlayer1(Player player);

	/**
	 * Sets connection to database in controller so it can be passed down to model
	 * @param dao
	 */
	public void setDatabaseConnection(DAOIF dao);
	/**
	 * Returns the current player to controller
	 * @return current player from game engine
	 */
	public Player getCurrentPlayer();
	/**
	 * Sets the cashout decision of pokergame.
	 * @param decision Boolean decision of user
	 */
	public void setCashout(Boolean decision);
	/**
	 * Sets the high or low value which user has decided in view.
	 * @param value String user high/low/suit guess
	 */
	public void setHighOrLow(String value);
}
