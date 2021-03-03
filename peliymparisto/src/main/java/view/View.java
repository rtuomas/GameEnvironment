package view;

import java.util.ArrayList;

import controller.Controller;
import controller.ControllerIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Player;

/**
 * The Graphical User Interface built with JavaFX
 * @author ---
 * @version 1.2 03.03.2021
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
	/** Button to enter the stats view */
	private Button enterStats;
	/** Button to close the program */
	private Button exitProgram;
	private Tab ranking, creditDevelopment;

	
	//PokerGameView variables
	private Text pokerGameCredits;
	private Text pokerGameBet;
	private ArrayList<ImageView> pokerGameCardImgs;
	private GridPane cardPane;
	private ArrayList<Integer> cardsToSwapIndexes = new ArrayList <Integer>();
	private boolean gameOn = false;

	//navBar components
	/** Button to go to main menu*/
	private Button homeButton;
	/** Button to open the register pop-up*/
	private Button registerButton;
	/** Input field for the user's email*/
	private TextField emailInput;
	/** Input field for the user's password*/
	private PasswordField passwordInput;
	/** Button to try signing in*/
	private Button signInButton;
	/**Text telling the amount of user's credits*/
	private Label creditView;
	private MenuButton playerMenu;
	/** Button to show users information*/
	private MenuItem playerInfoMI;
	/** Button for the user to log out*/
	private MenuItem logOutMI;
	/** Button to show information about the program*/
	private MenuItem infoMI;
	/** Button to exit the program*/
	private MenuItem exitMI;
	
	//registerform components
	private TextField firstNameRegisterInput;
	private TextField lastNameRegisterInput;
	private TextField profileNameRegisterInput;
	private TextField emailRegisterInput;
	private PasswordField passwordRegisterInput;
	private PasswordField passwordRegisterVerifyInput;
	private CheckBox creditTransferRegisterInput;
	private Button confirmRegisterButton;
	private Button cancelRegisterButton;


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
			createGUITransitions(primaryStage, mainView, mainMenu, pokerGame, settings, stats);

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
		Image aceCards = new Image("/images/aces.png", 500, 300, false, false);
		ImageView aceView = new ImageView(aceCards);
		
		BorderPane mainMenuView = new BorderPane();
		//mainMenuView.setPrefSize(400, 400);
		VBox napit = new VBox();
		napit.setAlignment(Pos.CENTER);
		napit.setPadding(new Insets(10, 10, 10, 10));
		napit.setSpacing(10);
		napit.setPrefWidth(100);
		enterPokerGame = new Button("Pokeri");
		enterPokerGame.setMinWidth(napit.getPrefWidth());
		enterSettings = new Button("Asetukset");
		enterSettings.setMinWidth(napit.getPrefWidth());
		enterStats = new Button("Tilastot");
		enterStats.setMinWidth(napit.getPrefWidth());
		exitProgram = new Button("Lopeta");
		exitProgram.setMinWidth(napit.getPrefWidth());
		napit.getChildren().addAll(aceView, enterPokerGame, enterSettings, enterStats, exitProgram);
		
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
		passwordInput = new PasswordField();
		passwordInput.setPromptText("Syötä salasana");
		signInButton = new Button("Kirjaudu");
		
		Label creditLabel = new Label("Saldo: ");
		creditView = new Label(String.valueOf(this.player.getCredits()));
		creditView.setStyle("-fx-font-weight: bold; -fx-border-color: black; -fx-background-color: #c4d8de;");
		creditView.setPadding(new Insets(4, 4, 4, 4));

		playerMenu = new MenuButton("Tester");
		playerMenu.setGraphic(new ImageView(user));
		playerInfoMI = new MenuItem("Näytä tiedot");
		logOutMI = new MenuItem("Kirjaudu ulos");
		playerMenu.getItems().addAll(playerInfoMI, logOutMI);
		MenuButton menu2 = new MenuButton();
		menu2.setGraphic(new ImageView(settings));
		infoMI = new MenuItem("Lisätietoja");
		exitMI = new MenuItem("Lopeta ohjelma");
		menu2.getItems().addAll(infoMI, exitMI);
		
		navBar.getChildren().addAll(homeButton, registerButton, signInLabel, emailInput, passwordInput, signInButton, 
				creditLabel, creditView, playerMenu, menu2);
		navBar.setAlignment(Pos.CENTER);
		navBar.setPadding(new Insets(5, 5, 5, 5));
		HBox.setMargin(registerButton, new Insets(0, 10, 0, 10));
		HBox.setMargin(signInLabel, new Insets(0, 10, 0, 0));
		HBox.setMargin(signInButton, new Insets(0, 10, 0, 0));
		HBox.setMargin(playerMenu, new Insets(0, 10, 0, 10));
		return navBar;
	}
	
	/**
	 * Contains the GUI for poker game
	 * @return AnchorPane type layout for the poker game
	 */
	private AnchorPane pokerGameBuilder() {
		
		// gamble button placement
		Button gamble = new Button ("Tuplaa");
		gamble.setLayoutX(367.0);
		gamble.setLayoutY(330.0);
		gamble.setPrefHeight(58.0);
		gamble.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(gamble, 11.39);
		AnchorPane.setRightAnchor(gamble, 135.0);
		
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
		AnchorPane.setLeftAnchor(plus, 450.0);
		plus.setOnAction(e -> {
			setPokerGameBet(controller.getBetIncrement());
		});
		
		// bet decrement button placement
		Button minus = new Button("-");
		minus.setLayoutX(219.0);
		minus.setLayoutY(349.0);
		AnchorPane.setBottomAnchor(minus, 24.4);
		AnchorPane.setLeftAnchor(minus, 350.0);
		minus.setOnAction(e -> {
			setPokerGameBet(controller.getBetDecrement());
		});
		
		// credits & bet placements
		pokerGameCredits = new Text();
		pokerGameBet = new Text();
		setPokerGamePlayerCredits();
		pokerGameCredits.setLayoutX(36.0);
		pokerGameCredits.setLayoutY(364.0);
		AnchorPane.setBottomAnchor(pokerGameCredits, 30.0);
		AnchorPane.setLeftAnchor(pokerGameCredits, 36.0);
		setPokerGameBet(controller.getBet());
		//pokerGameBet.setLayoutX(220.0); // 248
		//pokerGameBet.setLayoutY(367);
		AnchorPane.setBottomAnchor(pokerGameBet, 30.0);
		AnchorPane.setLeftAnchor(pokerGameBet, 385.0);
		
		
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
		
		// Gridpane for card images. Below space for ''locked''.
		cardPane = new GridPane ();
		cardPane.setLayoutX(8.0);
		cardPane.setLayoutY(158.0);
		cardPane.setPrefHeight(166.0);
		cardPane.setPrefWidth(579.0);
		AnchorPane.setTopAnchor(cardPane, 158.0);
		AnchorPane.setRightAnchor(cardPane, 10.0);
		AnchorPane.setLeftAnchor(cardPane, 10.0);
		AnchorPane.setBottomAnchor(cardPane, 75.0);
		
		RowConstraints cardRow = new RowConstraints();
		cardRow.setPrefHeight(30.0);
		cardRow.setMinHeight(10.0);
		cardRow.setVgrow(Priority.ALWAYS);
		cardRow.setFillHeight(true);
		//RowConstraints chosenRow = new RowConstraints(41);
		cardPane.getRowConstraints().add(cardRow);
		//cardPane.getRowConstraints().add(chosenRow);
		
	 
		for(int i = 0; i < 5; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setHgrow(Priority.ALWAYS);
			column.setPrefWidth(100.0);
			column.setMinWidth(10.0);
			column.setFillWidth(true);
			cardPane.getColumnConstraints().add(column);
		}
		
		//Initial card images
		pokerGameCardImgs = new ArrayList<ImageView>();
		Image startcard = new Image("/images/green_back.png");
		for(int i = 0; i < 5 ; i++) {
			Pane pane = new Pane();
			pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			ImageView img = new ImageView(startcard);
			img.fitWidthProperty().bind(pane.widthProperty());
			img.fitHeightProperty().bind(pane.heightProperty());
			pane.getChildren().add(img);
			pokerGameCardImgs.add(i, img);
			cardPane.add(pane, i, 0);
		}
		
		
		//Sets the whole AnchorPane with elements
		AnchorPane pokerGameView = new AnchorPane(play, gamble, plus, minus, pokerGameCredits, pokerGameBet, wintable, cardPane);
		pokerGameView.setPrefSize(600, 400);
		
		play.setOnAction(e -> {
			if(!gameOn) {
			controller.startPokerGame();
			} else {
			setSwappedCards();
			}
			gameOn = !gameOn;
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
		
		settingsView.setTop(save);
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
		statsView.setCenter(tabPane);
		return statsView;
	}
	
	/**
	 * This shows a window prompting the user to input their information for registration
	 * @param primaryStage this variable is used to link the new stage to the primaryStage
	 */
	private void showRegisterDialog(Stage primaryStage) {
		Stage rDialog = new Stage();
		rDialog.initModality(Modality.APPLICATION_MODAL);
		rDialog.initOwner(primaryStage);
		rDialog.setTitle("Rekisteröitymislomake");
		VBox rHeadline = new VBox();
		GridPane gridPane = new GridPane();
		HBox rButtons = new HBox();
        
		firstNameRegisterInput = new TextField();
		firstNameRegisterInput.setPromptText("Syötä etunimi");
		lastNameRegisterInput = new TextField();
		lastNameRegisterInput.setPromptText("Syötä sukunimi");
		profileNameRegisterInput = new TextField();
		profileNameRegisterInput.setPromptText("Valitse pelaajanimi");
		emailRegisterInput = new TextField();
		emailRegisterInput.setPromptText("Syötä sähköposti");
		passwordRegisterInput = new PasswordField();
		passwordRegisterInput.setPromptText("Valitse salasana");
		passwordRegisterVerifyInput = new PasswordField();
		passwordRegisterVerifyInput.setPromptText("Syötä salasana uudestaan");
		creditTransferRegisterInput = new CheckBox("Vie testikäyttäjänä kerätty saldo\nuudelle tilille (min. 100 krediittiä)");
		confirmRegisterButton = new Button("Rekisteröidy");
		cancelRegisterButton = new Button("Peruuta");
		
		rHeadline.getChildren().add(new Label("Luo tili"));
		rHeadline.setPadding(new Insets(10, 10, 10, 10));
		rHeadline.setAlignment(Pos.CENTER);
		
		gridPane.add(firstNameRegisterInput, 0, 0);
		gridPane.add(lastNameRegisterInput, 1, 0);
		gridPane.add(profileNameRegisterInput, 0, 1);
		gridPane.add(new Label("Tämä nimi näytetään muille pelaajille"), 1, 1);
		gridPane.add(emailRegisterInput, 0, 2, 2, 1);
		gridPane.add(passwordRegisterInput, 0, 3, 2, 1);
		gridPane.add(passwordRegisterVerifyInput, 0, 4, 2, 1);
		gridPane.add(creditTransferRegisterInput, 0, 5);
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setHgap(5);
		gridPane.setVgap(5);

		rButtons.getChildren().addAll(confirmRegisterButton, cancelRegisterButton);
		rButtons.setPadding(new Insets(10, 10, 10, 10));
		rButtons.setSpacing(10);
		rButtons.setAlignment(Pos.CENTER);

		BorderPane rDialogView = new BorderPane();
		rDialogView.setTop(rHeadline);
		rDialogView.setCenter(gridPane);
		rDialogView.setBottom(rButtons);
		
        Scene rDialogScene = new Scene(rDialogView); //100,100
        rDialog.setScene(rDialogScene);
        createRegisterActions();
        rDialog.show();
	}
	
	/**	{@inheritDoc} */
	@Override
	public Player getPlayer() {
		return this.player;
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setDefaultPlayer(Player defaultPlayer) {
		this.player = defaultPlayer;
		//this is a bit stupid way to make sure that the method does not run updateToolBar() before all GUI components are created, PLS FIX
		if (creditView != null) { 
			updateToolBar();
		}
		System.out.println("Default player set");
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setCurrentPlayer(Player currentPlayer) {
		this.player = currentPlayer;
		updateToolBar();
		System.out.println("Player data updated");
	}
	
	/**
	 * This method updates all the information in the toolbar to match any changes on the player
	 */
	private void updateToolBar() {
		this.creditView.setText(String.valueOf(this.player.getCredits()));
		this.playerMenu.setText(this.player.getProfileName());
		this.emailInput.setText("");
		this.passwordInput.setText("");
	}

	/**
	 * This function will create the transitions between different views and also the exit button functionality
	 * @param primaryStage Current View in the program
	 * @param mainMenuScene Main menu view
	 * @param pokerGameScene Poker game view
	 * @param settingsScene Settings view
	 * @param statsScene Stats view
	 */
	private void createGUITransitions(Stage primaryStage, BorderPane mainView, BorderPane mainMenu, AnchorPane pokerGame, BorderPane settings, BorderPane stats) {
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
		homeButton.setOnAction(e -> {
			mainView.setCenter(mainMenu);
		});
		exitProgram.setOnAction(e -> Platform.exit());
		exitMI.setOnAction(e -> Platform.exit());
		signInButton.setOnAction(e -> controller.attemptLogIn());
		logOutMI.setOnAction(e -> controller.getDefaultPlayer());
		registerButton.setOnAction(e -> {
			if (this.player.getId() == 1001) { //if the current player is Tester, it means he/she has not logged in yet
				showRegisterDialog(primaryStage);
			} else {
				showRegistrationErrorAlreadyLoggedIn(); //User is prompted to log out before registering
			}
		});
	}
	
	/**
	 * This method has the functionality for the confirm and cancel buttons in Registration window
	 */
	private void createRegisterActions() {
		confirmRegisterButton.setOnAction(e -> controller.attemptRegistration());
		cancelRegisterButton.setOnAction(e -> {
			Stage stage = (Stage)cancelRegisterButton.getScene().getWindow();
			stage.close();
		});
	}
	
	/**
	 * Custom onClick handler for card images.
	 * @param img ImageView image of the card
	 * @param index int card index, helps to determine which card is clicked.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setImagesOnClick(final ImageView img, final int index) {
		img.setPickOnBounds(true);
	    img.setOnMouseClicked(new EventHandler() {
				@Override
				public void handle(Event event) {
					cardsToSwapIndexes.add(index);
				}
	    });
	}
	
	public void setPokerGamePlayerCredits () {
		pokerGameCredits.setText("Saldo: " + Double.toString(this.player.getCredits()));
	}
	
	public void setPokerGameBet (double bet) {
		pokerGameBet.setText("Panos: " + Double.toString(bet));
	}
	
	@Override
	public void setCards(ArrayList<String> cards) {
		Platform.runLater(() -> {
		for(int i = 0; i < cards.size(); i++) {
			Pane pane = new Pane();
			pane.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			Image newCard = new Image("/images/" + cards.get(i) + ".png");
			ImageView imageview = new ImageView(newCard);
			imageview.fitHeightProperty().bind(pane.heightProperty());
			imageview.fitWidthProperty().bind(pane.widthProperty());
			pane.getChildren().add(imageview);
			pokerGameCardImgs.add(i, imageview);
			cardPane.add(pane, i, 0);
			setImagesOnClick(pokerGameCardImgs.get(i), i);
		}
		});
	}

	@Override
	public void setScore(String score) {
		System.out.println(score);
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
		controller.setSwappedCardIndexes(cardsToSwapIndexes);
		cardsToSwapIndexes.clear();
		pokerGameCardImgs.clear();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showLogInError() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Varoitus");
		alert.setHeaderText("Varoitus - syötetty data ei kelpaa");
		alert.setContentText("Varmista että sähköposti ja salasana ovat oikein.");
		alert.showAndWait();
	}
	
	/**
	 * Tells the user that they cannot create an account if they are already logged in
	 */
	private void showRegistrationErrorAlreadyLoggedIn() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Varoitus");
		alert.setHeaderText("Varoitus - Olet jo kirjautunut sisään");
		alert.setContentText("Kirjaudu ulos luodaksesi uuden tilin.");
		alert.showAndWait();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorEmptyFields() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Varoitus");
		alert.setHeaderText("Varoitus - vaaditut tiedot puuttuvat");
		alert.setContentText("Varmista että etunimi, sukunimi, sähköposti, salasana ja salasanan vahvistus on syötetty.");
		alert.showAndWait();
	}

	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorPasswordsNotMatch() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Varoitus");
		alert.setHeaderText("Varoitus - salasanat eivät täsmää");
		alert.setContentText("Varmista että syöttämäsi salasanat vastaavat toisiaan.");
		alert.showAndWait();
	}

	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorEmailAlreadyExists() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Varoitus");
		alert.setHeaderText("Varoitus - sähköpostiosoite on jo käytössä");
		alert.setContentText("Tähän sähköpostiosoitteeseen liitetty tili on jo olemassa.\n\n"
				+ "Kirjaudu sisään tai luo uudet tunnukset käyttäen toista sähköpostiosoitetta.\n"
				+ "Jos et muista salasanaasi, ota yhteyttä tukeen.");
		alert.showAndWait();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorDatabase() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Varoitus");
		alert.setHeaderText("Varoitus - Tilin luonti ei onnistunut");
		alert.setContentText("Jokin meni pieleen, kokeile hetken kuluttua uudelleen.");
		alert.showAndWait();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void handleRegistrationSuccess() {
		Stage stage = (Stage)cancelRegisterButton.getScene().getWindow();
		stage.close();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Rekisteröityminen");
		alert.setHeaderText("Tilin luominen onnistui!");
		alert.setContentText("Sinut on kirjattu sisään ja voit jatkaa pelaamista uusilla tunnuksilla");
		alert.showAndWait();
	}

	/**	{@inheritDoc} */
	@Override
	public String getEmailInput() {
		return String.valueOf(this.emailInput.getText());
	}
	
	/**	{@inheritDoc} */
	@Override
	public String getPasswordInput() {
		return String.valueOf(this.passwordInput.getText());
	}

	/**	{@inheritDoc} */
	@Override
	public String getFirstNameRegInput() {
		return String.valueOf(this.firstNameRegisterInput.getText());
	}

	/**	{@inheritDoc} */
	@Override
	public String getLastNameRegInput() {
		return String.valueOf(this.lastNameRegisterInput.getText());
	}

	/**	{@inheritDoc} */
	@Override
	public String getProfileNameRegInput() {
		return String.valueOf(this.profileNameRegisterInput.getText());
	}
	
	/**	{@inheritDoc} */
	@Override
	public String getEmailRegInput() {
		return String.valueOf(this.emailRegisterInput.getText());
	}

	/**	{@inheritDoc} */
	@Override
	public String getPasswordRegInput() {
		return String.valueOf(this.passwordRegisterInput.getText());
	}

	/**	{@inheritDoc} */
	@Override
	public String getPasswordRegVerInput() {
		return String.valueOf(this.passwordRegisterVerifyInput.getText());
	}

	/**	{@inheritDoc} */
	@Override
	public Boolean getCreditTransferRegInput() {
		return this.creditTransferRegisterInput.isSelected();
	}

}
