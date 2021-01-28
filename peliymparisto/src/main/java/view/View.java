package view;

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
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
			
	        Scene mainMenuScene = new Scene(mainMenu);
	        Scene pokerGameScene = new Scene(pokerGame);
	        Scene settingsScene = new Scene(settings);
	        Scene statsScene = new Scene(stats);
	        
	        createGUITransitions(primaryStage, mainMenuScene, pokerGameScene, settingsScene, statsScene);
	        
	        primaryStage.setScene(mainMenuScene);
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
	 * Contains the GUI for poker game
	 * @return AnchorPane type layout for the poker game
	 */
	private AnchorPane pokerGameBuilder() {
		backToMainMenu1 = new Button("Takaisin");
		AnchorPane.setLeftAnchor(backToMainMenu1, 5.0);
		AnchorPane.setTopAnchor(backToMainMenu1, 5.0);
		
		Button gamble = new Button ("Tuplaa");
		gamble.setLayoutX(367.0);
		gamble.setLayoutY(330.0);
		gamble.setPrefHeight(58.0);
		gamble.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(gamble, 11.39);
		
		Button play = new Button("Pelaa");
		play.setLayoutX(487.0);
		play.setLayoutY(331.0);
		play.setPrefHeight(58.0);
		play.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(play, 11.39);
		AnchorPane.setRightAnchor(play, 14.59);
		
		Button plus = new Button("+");
		plus.setLayoutX(324.0);
		plus.setLayoutY(350.0);
		AnchorPane.setBottomAnchor(plus, 24.4);
		
		Button minus = new Button("-");
		minus.setLayoutX(219.0);
		minus.setLayoutY(349.0);
		AnchorPane.setBottomAnchor(minus, 24.4);
		
		Text credits = new Text("Saldo: 100");
		credits.setLayoutX(36.0);
		credits.setLayoutY(364.0);
		Text bet = new Text("Panos: 1,20");
		bet.setLayoutX(248.0);
		bet.setLayoutY(367);
		
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
		
		AnchorPane pokerGameView = new AnchorPane(backToMainMenu1, play, gamble, plus, minus, credits, bet, wintable, cardPane);
		pokerGameView.setPrefSize(600, 400);
		
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
		
		Statistics stats = new Statistics();
		LineChart<Number, Number> lineChart = stats.getLineChart();
		
		statsView.setPrefSize(500, 500);
		backToMainMenu3 = new Button("Takaisin");
		statsView.setTop(backToMainMenu3);
		statsView.setCenter(lineChart);
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
	private void createGUITransitions(Stage primaryStage, Scene mainMenuScene, Scene pokerGameScene, Scene settingsScene, Scene statsScene) {
		enterPokerGame.setOnAction(e -> {
			primaryStage.setScene(pokerGameScene);
		});
		enterSettings.setOnAction(e -> {
			primaryStage.setScene(settingsScene);
		});
		enterStats.setOnAction(e -> {
			primaryStage.setScene(statsScene);
		});
		backToMainMenu1.setOnAction(e -> {
			primaryStage.setScene(mainMenuScene);
		});
		backToMainMenu2.setOnAction(e -> {
			primaryStage.setScene(mainMenuScene);
		});
		backToMainMenu3.setOnAction(e -> {
			primaryStage.setScene(mainMenuScene);
		});
		exitProgram.setOnAction(e -> Platform.exit());
	}

}
