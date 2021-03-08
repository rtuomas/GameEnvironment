package view;

import java.util.ArrayList;

import model.Player;

/**
 * The interface for the GUI. The Controller handles GUI related traffic through this interface.
 * @author ---
 * @version 1.3 03.03.2021
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
	 * Fetches the email from textfield
	 * @return String form of email
	 */
	public String getEmailInput();
	/**
	 * Fetches the password from textfield
	 * @return String form of password
	 */
	public String getPasswordInput();
	/**
	 * Shows error message incase of failure with login
	 */
	public void showLogInError();
	/**
	 * Shows error message with registration if one or more of required fields are empty
	 */
	public void showRegistrationErrorEmptyFields();
	/**
	 * Shows error message with registration if password does not match with retyped password
	 */
	public void showRegistrationErrorPasswordsNotMatch();
	/**
	 * Shows error message with registration if email is allready in use
	 */
	public void showRegistrationErrorEmailAlreadyExists();
	/**
	 * Shows error message with registration if there was an error with saving the information to database
	 */
	public void showRegistrationErrorDatabase();
	/**
	 * Notifies the user about the success of registration
	 * Closes the registration window
	 */
	public void handleRegistrationSuccess();
	/**
	 * Fetches the first name from registration window
	 * @return String form of first name
	 */
	public String getFirstNameRegInput();
	/**
	 * Fetches the last name from registration window
	 * @return String form of last name
	 */
	public String getLastNameRegInput();
	/**
	 * Fetches the profile name from registration window
	 * @return String form of profile name
	 */
	public String getProfileNameRegInput();
	/**
	 * Fetches the email from registration window
	 * @return String form of first name
	 */
	public String getEmailRegInput();
	/**
	 * Fetches the password from registration window
	 * @return String form of first name
	 */
	public String getPasswordRegInput();
	/**
	 * Fetches the repeated password from registration window
	 * @return String form of repeated password
	 */
	public String getPasswordRegVerInput();
	/**
	 * Fetches the choice box choice from registration window
	 * @return Boolean which tells if the Choice box is chosen or not
	 */
	public Boolean getCreditTransferRegInput();
	
	//private void setNotification(String text); ??
	
	/**
	 * Updates poker game player credits text.
	 */
	public void setPokerGamePlayerCredits();
	
	/**
	 * Updates poker game bet text. Calls updateWintale so wintable gets updated bet.
	 * @param bet double bet amount
	 */
	public void setPokerGameBet(double bet);
}
