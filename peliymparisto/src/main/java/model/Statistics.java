package model;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class Statistics {
	private int[] dates = {1,2,3,4,5,6,7};
	private int[] values = {100,120,115,90,100,80,55};
	
	public Statistics() {
		
	}
	
	public LineChart<Number, Number> getLineChart() {
		final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("My cash");
        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");
        
        //Populating the chart
        for(int i=0;i<dates.length;i++) {
        	series.getData().add(new XYChart.Data(dates[i], values[i]));
        }
        lineChart.getData().add(series);
        return lineChart;
        
	}
	
}
