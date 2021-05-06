package controller;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;
import model.PlayedGame;
import model.Player;

/**
 * The interface for the Controller. All necessary methods for the functionality of the MVC model are found here.
 * @author Aki K, Tuomas R, Jere L, Ville R, Tatu N
 * @version 1.4 04.05.2021
 */
public interface ControllerIF {
	
	/**
	 * Method that starts running the poker game through input from GUI
	 */
	public void startPokerGame();
	/**
	 * Sends score to view
	 * @param score string hand score
	 */
	public void setScore(String score);
	/**
	 * Sends dealed cards to view.
	 * @param cards Card [] dealed cards
	 */
	public void showCards(Card [] cards);
	/**
	 * Transfers indexes of the cards that are going to get swapped to model.
	 * @param indexes of the cards
	 */
	public void setSwappedCardIndexes (ArrayList<Integer> indexes);
	LineChart<String, Number> getLineChart(int timestamp);
	ArrayList<Player> getRanking();
	/**
	 * Sets the default player as Tester when program starts
	 */
	public void getDefaultPlayer();
	/**
	 * Updates the Player variable in View
	 */
	public void setCurrentPlayer();
	
	/**
	 * Gets bet increment from model.
	 * @return double current bet amount
	 */
	public double getBetIncrement();
	
	/**
	 * Gets bet decrement from model.
	 * @return double current bet amount
	 */
	public double getBetDecrement();
	
	/**
	 * Gets current bet size from model
	 * @return double current bet amount
	 */
	public double getBet();
	/**
	 * Gets email and password from View and checks from database if they are correct
	 * Changes current player in View if everything is fine.
	 * Shows an error message if log in is not successful
	 */
	public void attemptLogIn();
	/**
	 * Gets registration information from View and checks if they are okay
	 * Creates a new Player in database if everything checks out.
	 * Shows error messages depending on the errors encountered
	 */
	public void attemptRegistration();

	public ArrayList<PlayedGame> getPlayedGames();
	/**
	 * Gets edited Player information from View and attempts to save them into database
	 * Updates the existing Player in database if everything checks out and then calls to show success message in view.
	 * If saving to database fails, shows an error message in View
	 */
	public void changePlayerInfo();
	/**
	 * Sends client message to the server 
	 * @param string
	 */
	public void sendMessage(String string);
	public void notifyCreditReset();
	/**
	 * Sends decision about the cashout to model.
	 * @param decision Boolean user cashout decision
	 */
  	public void setCashout(Boolean decision);
  	/**
  	 * Sends the user guess about the high or low to model from view.
  	 * @param value String user guess
  	 */
  	public void setHighOrLow(String value);
  	/**
  	 * Sends the drawn high or low card to view from model.
  	 * @param card String drawn card
  	 */
  	public void setHighOrLowCard(String card);
  	/**
  	 * Sends changed game state from model to view.
  	 * @param state String current game state
  	 */
  	public void setGameState(String state);
  	/**
  	 * Attempts to connect to chat server
  	 */
	public void initChatConnection();
	/**
	 * Appends a new chat message to the chat window in View
	 * @param message
	 */
	public void displayMessage(String message);
	/** 
	 * Returns decimal format of current language
	 * @return DecimalFormat current decimal format
	 */
	public DecimalFormat getDecimalFormat();
	
}
