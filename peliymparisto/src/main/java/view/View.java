package view;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	/** Button to close the program */
	private Button exitProgram;

	public static void main(String[] args) {
		launch(args);
	}
	
	//setting starting values
	@Override
	public void init(){
		controller = new Controller(); //add parameter later
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
			BorderPane pokerGame = pokerGameBuilder();
			BorderPane settings = settingsBuilder();
			
	        Scene mainMenuScene = new Scene(mainMenu);
	        Scene pokerGameScene = new Scene(pokerGame);
	        Scene settingsScene = new Scene(settings);
	        
	        createGUITransitions(primaryStage, mainMenuScene, pokerGameScene, settingsScene);
	        
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
		exitProgram = new Button("Lopeta");
		VBox napit = new VBox();
		napit.getChildren().addAll(enterPokerGame, enterSettings, exitProgram);
		mainMenuView.setCenter(napit);
		return mainMenuView;
	}
	
	/**
	 * Contains the GUI for poker game
	 * @return BorderPane type layout for the poker game
	 */
	private BorderPane pokerGameBuilder() {
		BorderPane pokerGameView = new BorderPane();
		pokerGameView.setPrefSize(400, 400);
		backToMainMenu1 = new Button("Takaisin");
		pokerGameView.setTop(backToMainMenu1);
		return pokerGameView;
	}
	
	/**
	 * Contains the GUI for settings
	 * @return BorderPane type layout for the settings
	 */
	private BorderPane settingsBuilder() {
		BorderPane settingsView = new BorderPane();
		settingsView.setPrefSize(400, 400);
		backToMainMenu2 = new Button("Takaisin");
		settingsView.setTop(backToMainMenu2);
		return settingsView;
	}

	/**
	 * This function will create the transitions between different views and also the exit button functionality
	 * @param primaryStage Current View in the program
	 * @param mainMenuScene Main menu view
	 * @param pokerGameScene Poker game view
	 * @param settingsScene Settings view
	 */
	private void createGUITransitions(Stage primaryStage, Scene mainMenuScene, Scene pokerGameScene, Scene settingsScene) {
		enterPokerGame.setOnAction(e -> {
			primaryStage.setScene(pokerGameScene);
		});
		enterSettings.setOnAction(e -> {
			primaryStage.setScene(settingsScene);
		});
		backToMainMenu1.setOnAction(e -> {
			primaryStage.setScene(mainMenuScene);
		});
		backToMainMenu2.setOnAction(e -> {
			primaryStage.setScene(mainMenuScene);
		});
		exitProgram.setOnAction(e -> Platform.exit());
	}

}
