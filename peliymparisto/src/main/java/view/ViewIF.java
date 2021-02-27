package view;

import java.util.ArrayList;

import model.Player;

/**
 * The interface for the GUI. The Controller handles GUI related traffic through this interface.
 * @author ---
 * @version 1.0 26.01.2021
 */
public interface ViewIF {
	
	//getters and setters for transporting requests and responses between GUI and game engines
	
	/**
	 * Sends player through controller to model.
	 * @return player
	 */
	public Player getPlayer();
	/**
	 * Shows the cards in view.
	 * @param cards ArrayList<String> dealed cards.
	 */
	public void setCards(ArrayList<String> cards);
	/**
	 * Display game score in view.
	 * @param score string game score.
	 */
	public void setScore(String score);
	public void setSwappedCards();
	/**
	 * Updates the current Player variable in View
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(Player currentPlayer);
	
}
