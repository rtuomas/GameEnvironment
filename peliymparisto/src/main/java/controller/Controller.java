package controller;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import model.Card;
import model.DAO;
import model.DAOIF;
import model.ModelIF;
import model.PokerGameEngine;
import model.Statistics;
import view.ViewIF;

/**
 * The Controller which connects the GUI and chosen game engine together using MVC model.
 * @author ---
 * @version 1.2 01.03.2021
 */
public class Controller implements ControllerIF {
	
	private ModelIF model;
	private ViewIF view;
	private DAOIF dao;
	
	/**
	 * The constructor of the Controller class
	 * @param view is the graphical user interface
	 */
	public Controller(ViewIF view) {
		this.view = view;
		this.dao = new DAO();
	}
	
	/**	{@inheritDoc} */ //this line copies the JavaDoc from interface
	@Override
	public void startPokerGame() {
		model = new PokerGameEngine(this);
		model.setDatabaseConnection(this.dao);
		//model.setBet(view.getBet());
		model.setUpSinglePlayerGame(view.getPlayer());
		((Thread)model).start();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void getDefaultPlayer() {
		Platform.runLater(()->view.setDefaultPlayer(dao.getPlayer(1001)));
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setCurrentPlayer() {
		Platform.runLater(()->view.setCurrentPlayer(model.getCurrentPlayer()));
	}
	
	@Override
	public double getBetIncrement() {
		return PokerGameEngine.increaseBet();
	}
	
	@Override
	public double getBetDecrement() {
		return PokerGameEngine.decreaseBet();
	}
	
	@Override
	public double getBet() {
		return PokerGameEngine.getBet();
	}
	
	@Override
	public void setScore(String score) {
		Platform.runLater(()->view.setScore(score));
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
	
	/**	{@inheritDoc} */
	@Override
	public void attemptLogIn() {
		Platform.runLater(new Runnable(){ 
			public void run() {
				String email = view.getEmailInput();
				String password = view.getPasswordInput();
				if (email.isEmpty() || password.isEmpty()) {
					view.showLogInError();
				} else {
					String passwordToMatch = dao.searchEmail(email);
					if (passwordToMatch != null && password.equals(passwordToMatch)) {
						view.setCurrentPlayer(dao.getPlayer(email));
					} else {
						view.showLogInError();
					}
				}
			}
		});
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
