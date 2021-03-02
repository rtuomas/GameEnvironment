package view;

import java.util.ArrayList;

import model.Player;

/**
 * The interface for the GUI. The Controller handles GUI related traffic through this interface.
 * @author ---
 * @version 1.1 01.03.2021
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
	 * Sets the default Player when application starts
	 * @param defaultPlayer
	 */
	public void setDefaultPlayer(Player defaultPlayer);
	/**
	 * Updates the current Player variable in View
	 * @param currentPlayer
	 */
	public void setCurrentPlayer(Player currentPlayer);
	/**
	 * fetches the email from textfield
	 * @return String form of email
	 */
	public String getEmailInput();
	/**
	 * fetches the password from textfield
	 * @return String form of password
	 */
	public String getPasswordInput();
	/**
	 * Shows error message incase of failure with login
	 */
	public void showLogInError();
	
}
