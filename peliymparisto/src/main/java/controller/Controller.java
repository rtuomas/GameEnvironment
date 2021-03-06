package controller;

import java.io.IOException;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import client.ClientSocketHandler;
import client.EchoClient;
import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import model.Card;
import model.DAO;
import model.DAOIF;
import model.ModelIF;
import model.PlayedGame;
import model.Player;
import model.PokerGameEngine;
import model.Statistics;
import view.ViewIF;

/**
 * The Controller which connects the GUI and chosen game engine together using MVC model.
 * @author Aki K, Tuomas R, Jere L, Ville R, Tatu N
 * @version 1.6 04.05.2021
 */
public class Controller implements ControllerIF {
	
	/** Current game engine class*/
	private ModelIF model;
	/** Current Graphical User Interface*/
	private ViewIF view;
	/** Current Data Access Object which is responsible for the handling of database CRUD methods*/
	private DAOIF dao;
	/** Statistics class which takes the statistics from player and playedgame*/
	private Statistics stats;
	/** Connection for the Chat server*/
	private EchoClient clientConnection;
	/** Socket handler for the chat connection*/
	private ClientSocketHandler handler;
	/** Boolean variable which tells if game is currently running, true means yes*/
	private boolean gameStarted;
	
	/**
	 * The constructor of the Controller class
	 * @param view is the graphical user interface
	 */
	public Controller(ViewIF view) {
		this.view = view;
		this.dao = new DAO();
		this.stats = new Statistics(this.dao);
		this.gameStarted = false;
	}
	
	/**	{@inheritDoc} */ //this line copies the JavaDoc from interface
	@Override
	public void startPokerGame() {
		model = new PokerGameEngine(this);
		model.setDatabaseConnection(this.dao);
		//model.setBet(view.getBet());
		model.setUpSinglePlayerGame(view.getPlayer());
		((Thread)model).start();
		gameStarted = true;
	}
	
	/**	{@inheritDoc} */
	@Override
	public void getDefaultPlayer() {
		Platform.runLater(()-> {
			view.setDefaultPlayer(dao.getPlayer(1001));
			if (gameStarted) {
				model.setPlayer1(view.getPlayer());
			}
		});
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setCurrentPlayer() {
		Platform.runLater(()->view.setCurrentPlayer(model.getCurrentPlayer()));
	}
	
	/**	{@inheritDoc} */
	@Override
	public double getBetIncrement() {
		return PokerGameEngine.increaseBet();
	}
	
	/**	{@inheritDoc} */
	@Override
	public double getBetDecrement() {
		return PokerGameEngine.decreaseBet();
	}
	
	/**	{@inheritDoc} */
	@Override
	public double getBet() {
		return PokerGameEngine.getBet();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setScore(String score) {
		Platform.runLater(()->view.setScore(score));
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showCards(Card[] cards) {
		ArrayList<String> imgpaths = new ArrayList<String>();
		for(Card i : cards) {
			imgpaths.add(i.toString());
		}
		view.setCards(imgpaths);
	}
	
	/**	{@inheritDoc} */
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
					view.showLogInError(); //user is told that there was a problem with the input data
				} else {
					String passwordToMatch = dao.searchEmail(email);
					if (passwordToMatch != null && password.equals(passwordToMatch)) {
						view.setCurrentPlayer(dao.getPlayer(email));
					} else {
						view.showLogInError(); //user is told that there was a problem with the input data
					}
				}
			}
		});
	}
	
	/**	{@inheritDoc} */
	@Override
	public void attemptRegistration() {
		Platform.runLater(new Runnable(){ 
			public void run() {
				String firstName = view.getFirstNameRegInput();
				String lastName = view.getLastNameRegInput();
				String profileName = view.getProfileNameRegInput();
				String emailReg = view.getEmailRegInput();
				String pw1Reg = view.getPasswordRegInput();
				String pw2Reg = view.getPasswordRegVerInput();
				Boolean creditTransfer = view.getCreditTransferRegInput();
				
				if (firstName.isEmpty() || lastName.isEmpty() || emailReg.isEmpty() || pw1Reg.isEmpty() || pw2Reg.isEmpty()) {
					view.showRegistrationErrorEmptyFields();
				} else if(!pw1Reg.equals(pw2Reg)) {
					view.showRegistrationErrorPasswordsNotMatch();
				} else if(dao.searchEmail(emailReg) != null) {
					view.showRegistrationErrorEmailAlreadyExists();
				} else {
					Player player = new Player(firstName, lastName, emailReg, pw1Reg);
					if (!profileName.isEmpty()) { //if the profile name was not given, the default player name will be Player
						player.setProfileName(profileName);
					}
					if (creditTransfer) { //this checks if the checkbox for credit transfer was chosen
						double testerCredits = view.getPlayer().getCredits();
						if (testerCredits > 100) { //The testers credit amount is copied to the new account if it is over 100 credits
							player.setCredits(testerCredits);
						}
					}
					boolean check = dao.createPlayer(player); //creating a new Player into database
					if (check) {
						view.setCurrentPlayer(player); //logging in with new Player
						view.handleRegistrationSuccess(); //inform the user of success, close registration window
					} else {
						view.showRegistrationErrorDatabase(); //shows default error message if the player information could not be created to database
					}
				}
			}
		});
		
	}

	@Override
	public LineChart<String, Number> getLineChart(int count) {
		try {
			stats.setPlayerId(view.getPlayer().getId());
			return stats.getLineChart(count);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@Override
	public ArrayList<Player> getRanking() {
		try {
			return stats.getRanking();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public ArrayList<PlayedGame> getPlayedGames() {
		try {
			return stats.getPlayedGames();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	/**	{@inheritDoc} */
	@Override
	public void changePlayerInfo() {
		Platform.runLater(()-> {
			Player player = view.getPlayer();
			boolean check = dao.updatePlayer(player);
			if (check) {
				view.setCurrentPlayer(player); //to use other update methods associated with setCurrentPlayer
				view.handlePlayerInfoChangeSuccess(); //inform the user of success, close player information window
			} else {
				view.showPlayerInfoChangeErrorDatabase(); //shows default error message if the player information could not be updated to database
			}
		});
	}

	public void notifyCreditReset(){
	  view.notifyCreditReset();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setCashout(Boolean decision) {
		model.setCashout(decision);
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setHighOrLow(String value) {
		model.setHighOrLow(value);
	}

	/**	{@inheritDoc} */
	@Override
	public void setHighOrLowCard(String card) {
		view.setHighOrLowCard(card);
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setGameState(String state) {
		view.setGameState(state);
	}
	
	/**	{@inheritDoc} */
	@Override
	public void sendMessage(String msg) {
		if (clientConnection != null) {
			handler.sendMessage(msg);
		} else {
			displayMessage("Viesti?? ei voitu l??hett????");
		}
	}
	
	/**	{@inheritDoc} */
	@Override
	public void initChatConnection() {
		if (clientConnection == null) { //singleton connection, could make class singleton but uses controller as parameter
			clientConnection = new EchoClient(this);
			try {
				handler = clientConnection.start();
				displayMessage("Yhteys chat palvelimeen muodostettu");
			} catch (SocketException e) {
				displayMessage("Yhteytt?? chat palvelimeen ei voitu muodostaa");
				clientConnection = null;
				//e.printStackTrace();
			} catch (IOException e) {
				//view.displayConnectionError();
				displayMessage("Virhe chat palvelimella");
				e.printStackTrace();
			}
		} else {
			displayMessage("Yhteys chat palvelimeen on jo muodostettu");
			System.out.println("Using previous chat connection");
		}
		
	}
	
	/**	{@inheritDoc} */
	@Override
	public void displayMessage(String message) {
		view.displayMessage(message);
	}
	
	/**	{@inheritDoc} */
	@Override
	public DecimalFormat getDecimalFormat() {
		return view.getDecimalFormat();
	}
}
