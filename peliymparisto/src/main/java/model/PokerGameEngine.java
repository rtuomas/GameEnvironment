package model;

import java.util.ArrayList;
import java.util.Arrays;

import controller.ControllerIF;
import javafx.scene.chart.LineChart;

/**
 * The game engine for the poker game. It connects all the different classes needed to run the poker game.
 * @author ---
 * @version 1.2 18.02.2021
 */
public class PokerGameEngine extends Thread implements ModelIF {
	
	/** Interface with a connection to GUI */
	private ControllerIF controller;
	/** Interface with a connection to DAO */
	private DAOIF dao;
	//Players bet (starting with 1.00)
	private double bet;
	private Deck deck;

	private Hand hand;
	private Player player;

	//Player variables
	private Player player1;
	private Player player2;
	//Game variables
	private int winner;
	//Statistics
	private Statistics stats;
	
	/**
	 * Constructor for the poker game engine.
	 * Controller and DAO are not needed for CLI to operate!
	 * @param controller is the class which started running the poker game
	 */
	public PokerGameEngine(ControllerIF controller) {
		this.controller = controller;
		this.dao = new DAO();
		this.stats = new Statistics();
	}
	
	/**
	 * This method uses Threads to run multiple events in the GUI simultaneously
	 */
	public void run() {
		//game functionality
		deck = new Deck ();
		deck.shuffle();
				// dealHand -> hand to controller -> hand to view
				// update credits -> controller -> view
				// get score -> controller -> view
				// database stuff
				// thread ends
		dealCards();
		updateCredits();
		setScore();
		//endGame() //called when the game ends
	}
	
	/**
	 * Controller calls this method before starting the game
	 */
	public void setUpSinglePlayerGame() {
		this.player2 = dao.getPlayer(1000);
	}
	
	/**
	 * This method is ran at the end of the game to save the played game into database and update credit amounts to players.
	 */
	public void endGame() {
		//specify who is winner before this method
		double crForP1;
		double crForP2;
		int p1ID = this.player1.getId();
		int p2ID = this.player2.getId();
		if (this.winner == this.player1.getId()) {
			crForP1 = this.player1.getCredits() + this.bet;
			crForP2 = this.player2.getCredits() - this.bet;
		} else {
			crForP1 = this.player1.getCredits() - this.bet;
			crForP2 = this.player2.getCredits() + this.bet;
		}
		PlayedGame currentGame = new PlayedGame(p1ID, p2ID, "poker", this.winner, this.bet, crForP1, crForP2);
		dao.createPlayedGame(currentGame);
		this.player1.setCredits(crForP1);
		this.player2.setCredits(crForP2);
		dao.updatePlayer(player1);
		dao.updatePlayer(player2);
	}
	
	@Override
	public void dealCards () {
		this.hand = new Hand(deck);
		controller.showCards(this.hand.getHand());
	}
	
	@Override
	public void setBet(double bet) {
		this.bet = bet;
	}
	
	@Override
	public void setScore () {
		controller.setScore(this.hand.getScore().name());
	}
	
	@Override
	public void setPlayer (String name) {
		Player [] players = this.dao.readPlayers();
		String [] names = name.split(" ");
		for(Player p : players) {
			if(p.getFirstName().equals(names[0]) && p.getLastName().equals(names[1])) {
				this.player = p;
			}
		}
	}
	
	
	@Override
	public double getCredits () {
		return (double)this.player.getCredits();
	}
	
	@Override
	public void updateCredits () {
		if(this.hand.getScore() != HandValue.NO_WIN) {
			double win = this.hand.getScore().getMultiplier() * this.bet;
			this.player.setCredits(this.player.getCredits() + (int)win);
			controller.setCredits();
		}
		this.player.setCredits(this.player.getCredits() - (int)this.bet);
		controller.setCredits();
	}
	
	@Override
	public LineChart<Number, Number> getLineChart() {
		//Statistics stats = new Statistics();
		LineChart<Number, Number> lineChart = stats.getLineChart();
		
		return lineChart;
	}
	
	@Override
	public String[] getRanking(){
		//Statistics stats = new Statistics();
		return stats.getRanking();
	}
}
