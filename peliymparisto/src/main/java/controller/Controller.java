package controller;

import java.util.ArrayList;

import javafx.scene.chart.LineChart;
import model.Card;
import model.ModelIF;
import model.PokerGameEngine;
import model.Statistics;
import view.ViewIF;

/**
 * The Controller which connects the GUI and chosen game engine together using MVC model.
 * @author ---
 * @version 1.1 15.02.2021
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
		//model.setBet(view.getBet());
		model.setPlayer(view.getPlayer());
		//model.setPlayer1(view.getPlayerInfo()); //not implemented yet
		//model.setUpSinglePlayerGame(); //needs previous function to work
		((Thread)model).start();
	}
	
	public double getBetIncrement() {
		return PokerGameEngine.increaseBet();
	}
	
	public double getBetDecrement() {
		return PokerGameEngine.decreaseBet();
	}
	
	public double getBet() {
		return PokerGameEngine.getBet();
	}
	
	@Override
	public void setScore(String score) {
		view.setScore(score);
	}
	
	@Override
	public void setCredits () {
		view.setCredits(model.getCredits());
	}
	
	@Override
	public void updateCredits() {
		model.updateCredits();
	}
	
	@Override
	public void showCards(Card[] cards) {
		ArrayList<String> imgpaths = new ArrayList<String>();
		for(Card i : cards) {
			imgpaths.add(i.toString());
		}
		view.setCards(imgpaths);
	}
	
	@Override
	public void setSwappedCardIndexes (ArrayList<Integer> indexes) {
		model.setCardsToSwapIndexes(indexes);
	}

	@Override
	public LineChart<Number, Number> getLineChart() {
		try {
			Statistics stats = new Statistics();
			return stats.getLineChart();
			//return model.getLineChart();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	
	@Override
	public String[] getRanking() {
		try {
			Statistics stats = new Statistics();
			return stats.getRanking();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
}
