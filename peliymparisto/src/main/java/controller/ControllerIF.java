package controller;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;
import model.HandValue;
import model.PlayedGame;
import model.Player;

/**
 * The interface for the Controller. All necessary methods for the functionality of the MVC model are found here.
 * @author ---
 * @version 1.3 03.03.2021
 */
public interface ControllerIF {
	
	//methods that transmit info from View to Model -->
	
	/**
	 * Method that starts running the poker game through input from GUI
	 */
	public void startPokerGame();
	
	//methods that transmit info from Model to View -->
	
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
	 * Sends credits to view.
	 */
	//public void setCredits();
	public void setSwappedCardIndexes (ArrayList<Integer> indexes);
	
	//hybrid methods -->
	
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

  public void notifyCreditReset();
}
