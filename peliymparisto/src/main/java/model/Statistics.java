package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

/**
 * This class is responsible for creating and passing statistics in statistics view.
 * @author Tuomas Rajala
 * @version 3.1 9.3.2021
 */
public class Statistics {
	
	/*Current players id*/
	private int playerId;
	/*Only one dao is made*/
	private DAOIF dao;
	
	public Statistics(DAOIF dao) {
		this.dao = dao;
	}

	/**
	 * Fetches all necessary data from database, creates LineChart object, fills it and returns it.
	 * @param a integer for counting how many results is fetched from database.
	 * @return LineChart object.
	 */
	public LineChart<String, Number> getLineChart(int count) {
		List[] creditsList = this.dao.readCredits(this.playerId, count);
		final Axis<String> xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        
        xAxis.setLabel("Päivämäärä");
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis); //creating the chart
        lineChart.setTitle("Saldoni");
        XYChart.Series series = new XYChart.Series();
        series.setName("Yllä näet saldosi historiallisen kehityksen");
        for(int i=0;i<creditsList[0].size();i++) {
        	series.getData().add(new XYChart.Data(creditsList[0].get(i), creditsList[1].get(i)));
        }
        lineChart.getData().add(series);
        return lineChart;
	}
	
	/**
	 * Passes ranking data
	 * @return ArrayList. Data from database.
	 */
	public ArrayList<Player> getRanking() {
		return this.dao.readRankings();
	}

	/**
	 * Passes signed players played games from database.
	 * @return ArrayList
	 */
	public ArrayList<PlayedGame> getPlayedGames() {
		return this.dao.readPlayedGames(this.playerId);
	}

	/**
	 * Sets new players id.
	 * @param integer. players new id.
	 */
	public void setPlayerId(int id) {
		this.playerId = id;
	}
}
