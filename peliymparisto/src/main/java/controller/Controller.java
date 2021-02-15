package controller;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;
import model.ModelIF;
import model.PokerGameEngine;
import view.ViewIF;

/**
 * The Controller which connects the GUI and chosen game engine together using MVC model.
 * @author ---
 * @version 1.0 26.01.2021
 */
public class Controller implements ControllerIF {
	
	private ModelIF model;
	private ViewIF view;
	
	/**
	 * The constructor of the Controller class
	 * @param view is the graphical user interface
	 */
	public Controller(ViewIF view) {
		this.view = view;
	}
	
	/**	{@inheritDoc} */ //this line copies the JavaDoc from interface
	@Override
	public void startPokerGame() {
		model = new PokerGameEngine(this);
		((Thread)model).start();
	}
	
	@Override
	public ArrayList<String> dealCards () {
		Card cards [] = model.dealCards();
		ArrayList<String> imgpaths = new ArrayList<String>();
		for(Card i : cards) {
			imgpaths.add(i.toString());
		}
		
		return imgpaths;
	}
	
	@Override
	public LineChart<Number, Number> getLineChart() {
		try {
			return model.getLineChart();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	@Override
	public String[] getRanking() {
		try {
			return model.getRanking();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
}
