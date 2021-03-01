package controller;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;
import model.HandValue;
import model.Player;

/**
 * The interface for the Controller. All necessary methods for the functionality of the MVC model are found here.
 * @author ---
 * @version 1.1 01.03.2021
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
	
	LineChart<Number, Number> getLineChart();

	String[] getRanking();
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
	 * Shows an error message if log in is not succesful
	 */
	public void attemptLogIn();
	
	
}
