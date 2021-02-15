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
	 * Deals cards and shuffles deck
	 * @return Card [] dealed card objects
	 */
	public Card [] dealCards();

	LineChart<Number, Number> getLineChart();

	String[] getRanking();
}
