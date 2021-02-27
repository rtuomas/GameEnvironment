package controller;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;
import model.HandValue;
import model.Player;

/**
 * The interface for the Controller. All necessary methods for the functionality of the MVC model are found here.
 * @author ---
 * @version 1.0 26.01.2021
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
	
	
}
