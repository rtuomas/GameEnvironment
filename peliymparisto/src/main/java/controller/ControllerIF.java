package controller;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;

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
	
	
	//hybrid methods -->
	/**
	 * Deals cards and creates strings for image paths
	 * @return ArrayList<String> dealed cards
	 */
	public ArrayList<String> dealCards();

	LineChart<Number, Number> getLineChart();

	String[] getRanking();
	

}
