package model;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;

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
	 * Sets the player of the game.
	 * @param name string player name
	 */
	public void setPlayer(String name);
	/**
	 * Returns credits after game.
	 * @return double credit amount
	 */
	public double getCredits();
	/**
	 * Updates credits after game.
	 */
	public void updateCredits();
	//public double decreaseBet();
	//public double increaseBet();

	LineChart<Number, Number> getLineChart();

	String[] getRanking();

	public void setCardsToSwapIndexes(ArrayList<Integer> indexes);
}
