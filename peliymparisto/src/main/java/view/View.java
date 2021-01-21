package view;

import java.text.DecimalFormat;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class View extends Application implements ViewIF {
	
	//variables here -->
	private ControllerIF controller;

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
	        
	        //createGUITransition() continue making transitions with this function
	        
	        primaryStage.setScene(mainMenuScene);
	        primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private BorderPane settingsBuilder() {
		BorderPane something = new BorderPane();
		return something;
	}

	private BorderPane pokerGameBuilder() {
		BorderPane something = new BorderPane();
		return something;
	}

	private BorderPane mainMenuBuilder() {
		BorderPane something = new BorderPane();
		return something;
	}

}
