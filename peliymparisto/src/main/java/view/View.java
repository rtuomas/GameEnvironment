package view;

import java.util.ArrayList;

import controller.Controller;
import controller.ControllerIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Card;
import model.Player;
import model.Statistics;

/**
 * The Graphical User Interface built with JavaFX
 * @author ---
 * @version 1.0 26.01.2021
 */
public class View extends Application implements ViewIF {
	
	//Normal variables here -->
	/** Controller which transfers info per mvc structure */
	private ControllerIF controller;
	private Player player;
	
	//GUI components here -->
	/** Button to enter the poker game view */
	private Button enterPokerGame;
	/** Button to enter the settings view */
	private Button enterSettings;
	/** Button to return to the mainMenu view from poker game*/
	private Button backToMainMenu1;
	/** Button to return to the mainMenu view from settings */
	private Button backToMainMenu2;
	/** Button to return to the mainMenu view from stats */
	private Button backToMainMenu3;
	/** Button to enter the stats view */
	private Button enterStats;
	/** Button to close the program */
	private Button exitProgram;
	private Tab ranking, creditDevelopment;
	//navBar components
	/** Button to go to main menu*/
	private Button homeButton;
	/** Button to open the register pop-up*/
	private Button registerButton;
	/** Input field for the user's email*/
	private TextField emailInput;
	/** Input field for the user's password*/
	private TextField passwordInput;
	/** Button to try signing in*/
	private Button signInButton;
	/**Text telling the amount of user's credits*/
	private Label creditView;
	/** Button to show users information*/
	private MenuItem playerInfoMI;
	/** Button for the user to log out*/
	private MenuItem logOutMI;
	/** Button to show information about the program*/
	private MenuItem infoMI;
	/** Button to exit the program*/
	private MenuItem exitMI;

	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Setting starting values
	 */
	@Override
	public void init(){
		//setting the chosen controller
		controller = new Controller(this);
		//setting a default player (Tester)
		controller.getDefaultPlayer();
	}
	
	@Override
	public Player getPlayer() {
		return this.player;
	}

	@Override
	public void setCurrentPlayer(Player currentPlayer) {
		this.player = currentPlayer;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			
			// this will handle the safe closing of the program
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
			
			primaryStage.setTitle("GameEnvironment");

			BorderPane mainMenu = mainMenuBuilder();
			AnchorPane pokerGame = pokerGameBuilder();
			BorderPane settings = settingsBuilder();
			BorderPane stats = statsBuilder();
			HBox navBar = navBarBuilder();
			
			BorderPane mainView = new BorderPane();
			mainView.setTop(navBar);
			mainView.setCenter(mainMenu);
			Scene mainScene = new Scene(mainView);
			createGUITransitions(mainView, mainMenu, pokerGame, settings, stats);

			primaryStage.setScene(mainScene);
	        primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Contains the GUI for main menu
	 * @return BorderPane type layout for the main menu
	 */
	private BorderPane mainMenuBuilder() {
		BorderPane mainMenuView = new BorderPane();
		mainMenuView.setPrefSize(400, 400);
		enterPokerGame = new Button("Pokeri");
		enterSettings = new Button("Asetukset");
		enterStats = new Button("Tilastot");
		exitProgram = new Button("Lopeta");
		VBox napit = new VBox();
		napit.getChildren().addAll(enterPokerGame, enterSettings, enterStats, exitProgram);
		mainMenuView.setCenter(napit);
		return mainMenuView;
	}
	
	/**
	 * Contains the navigation toolbar in the top of the program view
	 * @return HBox type assortment of buttons and labels for the toolbar
	 */
	private HBox navBarBuilder() {
		Image user = new Image("/images/user.png", 20, 20, false, false);
		Image settings = new Image("/images/settings.png", 20, 20, false, false);
		Image home = new Image("/images/home.png", 20, 20, false, false);
		
		HBox navBar = new HBox();
		
		homeButton = new Button();
		homeButton.setGraphic(new ImageView(home));
		
		registerButton = new Button("Rekisteröidy");
		Label signInLabel = new Label("tai kirjaudu: ");
		
		emailInput = new TextField();
		emailInput.setPromptText("Syötä sähköposti");
		passwordInput = new TextField();
		passwordInput.setPromptText("Syötä salasana");
		signInButton = new Button("Kirjaudu");
		
		Label creditLabel = new Label("Saldo: ");
		creditView = new Label("100.00");
		creditView.setStyle("-fx-font-weight: bold; -fx-border-color: black; -fx-background-color: #c4d8de;");
		creditView.setPadding(new Insets(4, 4, 4, 4));

		MenuButton menu1 = new MenuButton("Testaaja");
		menu1.setGraphic(new ImageView(user));
		playerInfoMI = new MenuItem("Näytä tiedot");
		logOutMI = new MenuItem("Kirjaudu ulos");
		menu1.getItems().addAll(playerInfoMI, logOutMI);
		MenuButton menu2 = new MenuButton();
		menu2.setGraphic(new ImageView(settings));
		infoMI = new MenuItem("Lisätietoja");
		exitMI = new MenuItem("Lopeta ohjelma");
		menu2.getItems().addAll(infoMI, exitMI);
		
		navBar.getChildren().addAll(homeButton, registerButton, signInLabel, emailInput, passwordInput, signInButton, 
				creditLabel, creditView, menu1, menu2);
		navBar.setAlignment(Pos.CENTER);
		navBar.setPadding(new Insets(5, 5, 5, 5));
		HBox.setMargin(registerButton, new Insets(0, 10, 0, 10));
		HBox.setMargin(signInLabel, new Insets(0, 10, 0, 0));
		HBox.setMargin(signInButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(menu1, new Insets(0, 10, 0, 10));
		return navBar;
	}
	
	/**
	 * Contains the GUI for poker game
	 * @return AnchorPane type layout for the poker game
	 */
	private AnchorPane pokerGameBuilder() {
		
		// mainmenu button
		backToMainMenu1 = new Button("Takaisin");
		AnchorPane.setLeftAnchor(backToMainMenu1, 5.0);
		AnchorPane.setTopAnchor(backToMainMenu1, 5.0);
		
		// gamble button placement
		Button gamble = new Button ("Tuplaa");
		gamble.setLayoutX(367.0);
		gamble.setLayoutY(330.0);
		gamble.setPrefHeight(58.0);
		gamble.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(gamble, 11.39);
		
		// play button placement
		Button play = new Button("Pelaa");
		play.setLayoutX(487.0);
		play.setLayoutY(331.0);
		play.setPrefHeight(58.0);
		play.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(play, 11.39);
		AnchorPane.setRightAnchor(play, 14.59);
		
		// bet increment button placement
		Button plus = new Button("+");
		plus.setLayoutX(324.0);
		plus.setLayoutY(350.0);
		AnchorPane.setBottomAnchor(plus, 24.4);
		
		// bet decrement button placement
		Button minus = new Button("-");
		minus.setLayoutX(219.0);
		minus.setLayoutY(349.0);
		AnchorPane.setBottomAnchor(minus, 24.4);
		
		// credits & bet placements
		Text credits = new Text("Saldo: 100");
		credits.setLayoutX(36.0);
		credits.setLayoutY(364.0);
		Text bet = new Text("Panos: 1,20");
		bet.setLayoutX(248.0);
		bet.setLayoutY(367);
		
		// Gridpane for wintable
		GridPane wintable = new GridPane();
		wintable.setGridLinesVisible(true);
		wintable.setLayoutX(367.0);
		wintable.setLayoutY(20.0);
		wintable.setPrefHeight(128.0);
		wintable.setPrefWidth(222.0);
		AnchorPane.setTopAnchor(wintable, 20.0);
		AnchorPane.setRightAnchor(wintable, 10.59);
		
		for(int i = 0; i < 3; i++) {
			RowConstraints row = new RowConstraints(30);
			wintable.getRowConstraints().add(row);
		}
		
		for(int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPrefWidth(100.0);
			wintable.getColumnConstraints().add(column);
		}
		
		Text pair = new Text("Kaksi paria:");
		Text threeofkind = new Text("Kolmoset:");
		Text straight = new Text("Suora:");
		Text flush = new Text("Väri:");
		Text fullhouse = new Text("Täyskäsi:");
		Text fourofkind = new Text("Neloset:");
		Text straightflush = new Text("Värisuora:");
		Text fiveofkind = new Text ("Vitoset:");
		
		wintable.add(pair, 0, 3);
		wintable.add(threeofkind, 0, 2);
		wintable.add(straight, 0, 1);
		wintable.add(flush, 0, 0);
		wintable.add(fullhouse, 1, 3);
		wintable.add(fourofkind, 1, 2);
		wintable.add(straightflush, 1, 1);
		wintable.add(fiveofkind, 1, 0);
		
		// Gridpane for card images. Below space for ''locked''. Will remove gridlines when finished.
		GridPane cardPane = new GridPane ();
		cardPane.setGridLinesVisible(true);
		cardPane.setLayoutX(5.0);
		cardPane.setLayoutY(155.0);
		cardPane.setPrefHeight(167.0);
		cardPane.setPrefWidth(594.0);
		AnchorPane.setTopAnchor(cardPane, 155.0);
		AnchorPane.setRightAnchor(cardPane, 2.6);
		
		RowConstraints cardRow = new RowConstraints(125);
		RowConstraints chosenRow = new RowConstraints(41);
		cardPane.getRowConstraints().add(cardRow);
		cardPane.getRowConstraints().add(chosenRow);
		
		for(int i = 0; i < 5; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPrefWidth(118.0);
			cardPane.getColumnConstraints().add(column);
		}
		
		// Initial card images
		Image startcard = new Image("/images/green_back.png", 120, 128, false, false);
		for(int i = 0; i < 5 ; i++) {
			cardPane.add(new ImageView(startcard), i, 0);
		}
		
		//Sets the whole AnchorPane with elements
		AnchorPane pokerGameView = new AnchorPane(backToMainMenu1, play, gamble, plus, minus, credits, bet, wintable, cardPane);
		pokerGameView.setPrefSize(600, 400);
		
		// On action for play button, should be refactored
		play.setOnAction(e -> {
//				ArrayList<String> cards = controller.dealCards();
//			
//			for(int i = 0; i < cards.size(); i++) {
//				Image newCard = new Image("/images/" + cards.get(i) + ".png", 120, 128, false, false);
//				cardPane.add(new ImageView(newCard), i, 0);
//			}
		});
		
		return pokerGameView;
	}
	
	/**
	 * Contains the GUI for settings
	 * @return BorderPane type layout for the settings
	 */
	// Work in progress - Ville Riepponen
	private BorderPane settingsBuilder() {
		BorderPane settingsView = new BorderPane();
		settingsView.setPrefSize(400, 400);
		
		Slider volumeControl = new Slider(0, 1, 0.5);
		volumeControl.setShowTickMarks(true);
		volumeControl.setShowTickLabels(true);
		volumeControl.setMajorTickUnit(0.25f);
		volumeControl.setBlockIncrement(0.1f);
		volumeControl.setMaxWidth(200);
		BorderPane.setAlignment(volumeControl, Pos.CENTER);
		BorderPane.setMargin(volumeControl, new Insets(50, 50, 50, 50));
		
		Button save = new Button("Tallenna");
		
		BorderPane.setAlignment(save, Pos.CENTER);
		BorderPane.setMargin(save, new Insets(50, 50, 50, 50));
		
		backToMainMenu2 = new Button("Takaisin");
		
		settingsView.setCenter(save);
		settingsView.setTop(backToMainMenu2);
		settingsView.setCenter(volumeControl);
		
		return settingsView;
	}
	
	/**
	 * Contains the GUI for stats
	 * @return BorderPane type layout for the stats
	 */
	private BorderPane statsBuilder() {
		BorderPane statsView = new BorderPane();
		
		/*
		Statistics stats = new Statistics();
		LineChart<Number, Number> lineChart = stats.getLineChart();
		
		ListView listView = new ListView();
		String[] ranks = stats.getRanking();
		for(int i = 0;i<ranks.length;i++) {
			listView.getItems().add(ranks[i]);
		}
		*/
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		creditDevelopment = new Tab("Credits", new Label("This pane shows your credit development from the beginning"));
		ImageView growth = new ImageView(new Image("/images/growthtab.png", 25, 22, false, false));
		creditDevelopment.setGraphic(growth);
        ranking = new Tab("Ranking"  , new Label("Can you beat the best players?"));
        ImageView rank = new ImageView(new Image("/images/rankingtab.png", 25, 22, false, false));
        ranking.setGraphic(rank);
        //creditDevelopment.setContent(lineChart);
        //ranking.setContent(listView);
        tabPane.getTabs().add(creditDevelopment);
        tabPane.getTabs().add(ranking);
        
		statsView.setPrefSize(500, 500);
		backToMainMenu3 = new Button("Takaisin");
		statsView.setTop(backToMainMenu3);
		statsView.setCenter(tabPane);
		return statsView;
	}

	/**
	 * This function will create the transitions between different views and also the exit button functionality
	 * @param primaryStage Current View in the program
	 * @param mainMenuScene Main menu view
	 * @param pokerGameScene Poker game view
	 * @param settingsScene Settings view
	 * @param statsScene Stats view
	 */
	private void createGUITransitions(BorderPane mainView, BorderPane mainMenu, AnchorPane pokerGame, BorderPane settings, BorderPane stats) {
		enterPokerGame.setOnAction(e -> {
			mainView.setCenter(pokerGame);
		});
		enterSettings.setOnAction(e -> {
			mainView.setCenter(settings);
		});
		enterStats.setOnAction(e -> {
			mainView.setCenter(stats);
			System.out.println("STATS");
			fillStatistics();
		});
		backToMainMenu1.setOnAction(e -> {
			mainView.setCenter(mainMenu);
		});
		backToMainMenu2.setOnAction(e -> {
			mainView.setCenter(mainMenu);
		});
		backToMainMenu3.setOnAction(e -> {
			mainView.setCenter(mainMenu);
		});
		exitProgram.setOnAction(e -> Platform.exit());
	}

	@Override
	public void setCards(ArrayList<String> cards) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScore(String score) {
		// TODO Auto-generated method stub
		
	}

	private void fillStatistics() {
		
		LineChart<Number, Number> lineChart = controller.getLineChart();
		
		ListView listView = new ListView();
		String[] ranks = controller.getRanking();
		for(int i = 0;i<ranks.length;i++) {
			listView.getItems().add(ranks[i]);
		}
		
		creditDevelopment.setContent(lineChart);
        ranking.setContent(listView);		
	}

	@Override
	public void setSwappedCards() {
		// TODO Auto-generated method stub
		
	}

}
