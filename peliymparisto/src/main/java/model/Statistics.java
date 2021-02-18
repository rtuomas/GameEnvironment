package model;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Statistics {
	private int[] dates = {0,1,2,3};
	//private int[] values = {100,120,115,90,100,80,55};
	//private String[] ranks = {"1350, Pena Aarnio", "999, AI", "-5000, Jari Aarnio"};
	
	public Statistics() {
		
	}
	
	public LineChart<Number, Number> getLineChart() {
		
		DAOIF dao = new DAO();
		ArrayList<Double> creditsList = new ArrayList<>();
		creditsList = dao.readCredits(1001);
		ArrayList<Double> myCredits = new ArrayList<>();
		double temp = 0.0;
		for(Double i : creditsList) {
			temp += i;
			myCredits.add(temp);
		}
		
		
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("My cash");
        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");
        
        for(int i=0;i<myCredits.size();i++) {
        	series.getData().add(new XYChart.Data(dates[i], myCredits.get(i)));
        }
        
        /*
        //Populating the chart
        for(int i=0;i<dates.length;i++) {
        	series.getData().add(new XYChart.Data(dates[i], values[i]));
        }
        */
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
