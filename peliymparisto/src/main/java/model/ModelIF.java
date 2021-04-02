package model;

import java.util.ArrayList;

/**
 * The interface for game engines. The Controller is connected to this interface so it can call different game engines when needed.
 * @author ---
 * @version 1.0 26.01.2021
 */
public interface ModelIF {
	
	//The common methods for all game engines -->
	
	/**
	 * Deals cards in model.
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
	 * Sets connection to database in controller so it can be passed down to model
	 * @param dao
	 */
	public void setDatabaseConnection(DAOIF dao);

	public Player getCurrentPlayer(); //make non interface
	public void setCashout(Boolean decision);
	public void setHighOrLow(String value);
}
