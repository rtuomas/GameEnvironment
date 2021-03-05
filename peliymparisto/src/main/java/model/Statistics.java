package model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Statistics {
	private int[] dates = {0,1,2,3};
	//private int[] values = {100,120,115,90,100,80,55};
	//private String[] ranks = {"1350, Pena Aarnio", "999, AI", "-5000, Jari Aarnio"};
	private int playerId;
	
	public Statistics(int playerId) {
		this.playerId = playerId;
	}
	
	public Statistics() {}
	
	public LineChart<String, Number> getLineChart(int count) {
		
		DAOIF dao = new DAO();
		List[] creditsList = dao.readCredits(this.playerId, count);
		
		final Axis<String> xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        
        //creating the chart
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("My cash");
        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");
        
        for(int i=0;i<creditsList[0].size();i++) {
        	series.getData().add(new XYChart.Data(creditsList[0].get(i), creditsList[1].get(i)));
        }

        lineChart.getData().add(series);
        return lineChart;
        
	}
	
	/**
	 * TESTI!! JATKOSSA KONTROLLERIN KAUTTA
	 * @return
	 */
	public String[] getRanking() {
		DAOIF dao = new DAO();
		ArrayList<String> rankingList = new ArrayList<>();
		rankingList = dao.readRankings();
		String[] rankings = rankingList.toArray(new String[0]);
		return rankings;
	}
}
