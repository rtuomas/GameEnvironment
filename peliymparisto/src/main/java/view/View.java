package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import controller.Controller;
import controller.ControllerIF;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.HandValue;
import model.PlayedGame;
import model.Player;
import model.PlayerRanking;

/**
 * The Graphical User Interface built with JavaFX
 * @author ---
 * @version 1.6 06.03.2021
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
	private Tab ranking, creditDevelopment, playedGames;
	private ComboBox combobox;
	LineChart<String, Number> lineChart;
	private TableView tableViewRanks, tableViewGames;

	
	//PokerGameView variables
	private Text pokerGameCredits;
	private Text pokerGameBet;
	private GridPane cardPane;
	private ArrayList<Integer> cardsToSwapIndexes = new ArrayList<Integer>();
	private boolean gameOn = false;
	private Text notification;
	private StackPane [] imageStacks = new StackPane [5];
	private ImageView [] cardViews = new ImageView [5];
	private ImageView [] lockViews = new ImageView [5];
	private List <Text> winTableTexts = new ArrayList<Text>(); 

	//navBar components
	/** Button to go to main menu*/
	private Button homeButton;
	/** Button to open the register pop-up*/
	private Button registerButton;
	/** Text guide for toolbar*/
	private Label signInLabel;
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
	/** Button for the not logged in user to get info about forgotten password*/
	private MenuItem logInProblemMI;
	/** Button to show information about the program*/
	private MenuItem infoMI;
	/** Button to exit the program*/
	private MenuItem exitMI;
	
	//registerform components
	/** Input field for user's first name in the registration window */
	private TextField firstNameRegisterInput;
	/** Input field for user's last name in the registration window */
	private TextField lastNameRegisterInput;
	/** Input field for user's profile name in the registration window */
	private TextField profileNameRegisterInput;
	/** Input field for user's email in the registration window */
	private TextField emailRegisterInput;
	/** Input field for user's chosen password in the registration window */
	private PasswordField passwordRegisterInput;
	/** Input field for user's chosen password for the second time in the registration window */
	private PasswordField passwordRegisterVerifyInput;
	/** Choice box for user's choice if he wants to transfer accumulated credits in the registration window */
	private CheckBox creditTransferRegisterInput;
	/** Button to confirm registration in the registration window */
	private Button confirmRegisterButton;
	/** Button to cancel registration in the registration window */
	private Button cancelRegisterButton;
	
	//playerinformation components
	/** Button to save changes to player information in player info window */
	private Button savePlayerInfoButton;
	/** Button to cancel changes to player information in player info window */
	private Button cancelPlayerInfoButton;
	/** Button to open editing for player profile name in player info window */
	private Button editProfileNameButton;
	/** Button to open editing for player password in player info window */
	private Button editPasswordButton;
	/** Input field for user's new profile name in player info window */
	private TextField newProfileNameInput;
	/** Button to accept changes to profile name in profile name editing */
	private Button acceptPNEditsButton;
	/** Button to cancel changes to profile name in profile name editing */
	private Button cancelPNEditsButton;
	/** Input field for user's new password in player info window */
	private PasswordField newPasswordInput;
	/** Button to accept changes to password in password editing */
	private Button acceptPSEditsButton;
	/** Button to cancel changes to password in password editing */
	private Button cancelPSEditsButton;


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
			
			
			mainScene.getStylesheets().add("/styles/style.css");
			
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
		Image settings = new Image("/images/info.png", 20, 20, false, false);
		Image home = new Image("/images/home.png", 20, 20, false, false);
		
		HBox navBar = new HBox();
		
		homeButton = new Button();
		homeButton.setGraphic(new ImageView(home));
		
		registerButton = new Button("Rekisteröidy");
		signInLabel = new Label("tai kirjaudu: ");
		
		emailInput = new TextField();
		emailInput.setPromptText("Syötä sähköposti");
		passwordInput = new PasswordField();
		passwordInput.setPromptText("Syötä salasana");
		signInButton = new Button("Kirjaudu");
		
		Label creditLabel = new Label("Saldo: ");
		creditView = new Label(String.valueOf(this.player.getCredits()));
		creditView.setStyle("-fx-font-weight: bold; -fx-border-color: black; -fx-background-color: #c4d8de;");
		creditView.setPadding(new Insets(4, 4, 4, 4));

		playerMenu = new MenuButton("Ei kirjautunut");
		playerMenu.setGraphic(new ImageView(user));
		playerInfoMI = new MenuItem("Näytä pelaajatiedot");
		logOutMI = new MenuItem("Kirjaudu ulos");
		logInProblemMI = new MenuItem("Unohditko salasanasi");
		playerMenu.getItems().addAll(playerInfoMI, logOutMI, logInProblemMI);
		logOutMI.setVisible(false);
		playerInfoMI.setVisible(false);
		MenuButton menu2 = new MenuButton();
		menu2.setGraphic(new ImageView(settings));
		infoMI = new MenuItem("Lisätietoja ohjelmasta");
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
		Collections.addAll(cardsToSwapIndexes,0,1,2,3,4);
		
		//Notification text under cards
		notification = new Text("Valitse panos ja paina pelaa");
		AnchorPane.setBottomAnchor(notification, 70.0);
		notification.setLayoutX(270);
		notification.setFont(Font.font(24));
		
		// Top left corner image
		ImageView topLeftImg = new ImageView(new Image("/images/pokergame_top_left.png", 110, 125.4 , true, true));
		AnchorPane.setLeftAnchor(topLeftImg, 13.0);
		AnchorPane.setTopAnchor(topLeftImg, 5.0);
		
		
		// gamble button placement
		Button gamble = new Button ("Tuplaa");
		gamble.setPrefHeight(58.0);
		gamble.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(gamble, 11.39);
		AnchorPane.setRightAnchor(gamble, 135.0);
		
		// play button placement
		Button play = new Button("Pelaa");
		play.setPrefHeight(58.0);
		play.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(play, 11.39);
		AnchorPane.setRightAnchor(play, 14.59);
		
		// bet increment button placement
		Button plus = new Button("+");
		plus.setPrefSize(30, 30);
		AnchorPane.setBottomAnchor(plus, 24.4);
		AnchorPane.setLeftAnchor(plus, 470.0);
		plus.setOnAction(e -> {
			setPokerGameBet(controller.getBetIncrement());
		});
			
		// bet decrement button placement
		Button minus = new Button("-");
		minus.setPrefSize(30, 30);
		AnchorPane.setBottomAnchor(minus, 24.4);
		AnchorPane.setLeftAnchor(minus, 340.0);
		minus.setOnAction(e -> {
			setPokerGameBet(controller.getBetDecrement());
		});
		
		
		// Gridpane for wintable
		GridPane wintable = new GridPane();
		wintable.setGridLinesVisible(true);
		AnchorPane.setTopAnchor(wintable, 0.0);
		AnchorPane.setRightAnchor(wintable, 13.0);
		
		for(int i = 0; i < 4; i++) {
			RowConstraints row = new RowConstraints(33);
			wintable.getRowConstraints().add(row);
		}
		
		for(int i = 0; i < 2; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPrefWidth(135.0);
			wintable.getColumnConstraints().add(column);
		}
		
		// Texts for wintable
		Text acepair = new Text();
		Text pair = new Text();
		Text threeofkind = new Text();
		Text straight = new Text();
		Text flush = new Text();
		Text fullhouse = new Text();
		Text fourofkind = new Text();
		Text straightflush = new Text();
		Collections.addAll(winTableTexts, acepair, pair, threeofkind, straight, flush, fullhouse, fourofkind, straightflush);
		for(Text t : winTableTexts) {
			t.setFont(Font.font(16));
			wintable.setMargin(t, new Insets(5,5,5,5));
		}
		
		wintable.add(acepair, 0, 3);
		wintable.add(pair, 1, 3);
		wintable.add(threeofkind, 0, 2);
		wintable.add(straight, 1, 2);
		wintable.add(flush, 0, 1);
		wintable.add(fullhouse, 1, 1);
		wintable.add(fourofkind, 0, 0);
		wintable.add(straightflush, 1, 0);
		
		// credits & bet placements
		pokerGameCredits = new Text();
		pokerGameCredits.setFont(Font.font(16));
		pokerGameBet = new Text();
		pokerGameBet.setFont(Font.font(16));
		setPokerGamePlayerCredits();
		AnchorPane.setBottomAnchor(pokerGameCredits, 30.0);
		AnchorPane.setLeftAnchor(pokerGameCredits, 36.0);
		setPokerGameBet(controller.getBet());
		AnchorPane.setBottomAnchor(pokerGameBet, 30.0);
		AnchorPane.setLeftAnchor(pokerGameBet, 385.0);
		
		// Gridpane for card images
		cardPane = new GridPane ();
		cardPane.setPrefHeight(166.0);
		cardPane.setPrefWidth(579.0);
		AnchorPane.setTopAnchor(cardPane, 140.0);
		AnchorPane.setRightAnchor(cardPane, 10.0);
		AnchorPane.setLeftAnchor(cardPane, 10.0);
		AnchorPane.setBottomAnchor(cardPane, 105.0);
		
		RowConstraints cardRow = new RowConstraints();
		cardRow.setPrefHeight(30.0);
		cardRow.setMinHeight(10.0);
		cardRow.setVgrow(Priority.ALWAYS);
		cardRow.setFillHeight(true);
		cardPane.getRowConstraints().add(cardRow);
		
	 
		for(int i = 0; i < 5; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setHgrow(Priority.ALWAYS);
			column.setPrefWidth(100.0);
			column.setMinWidth(10.0);
			column.setFillWidth(true);
			cardPane.getColumnConstraints().add(column);
		}
		
		
		
		// Initial card images
		for(int i = 0; i < imageStacks.length ; i++) {
			imageStacks[i] = new StackPane();
			imageStacks[i].setMinHeight(0);
			imageStacks[i].setMinWidth(0);
			imageStacks[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			cardViews[i] = new ImageView(new Image("/images/green_back.png"));
			cardViews[i].fitWidthProperty().bind(imageStacks[i].widthProperty());
			cardViews[i].fitHeightProperty().bind(imageStacks[i].heightProperty());
			imageStacks[i].getChildren().add(cardViews[i]);
			lockViews[i] = new ImageView(new Image("/images/lukko.png", 75, 75, true, true));
			imageStacks[i].getChildren().add(lockViews[i]);
			lockViews[i].setVisible(false);
			cardPane.add(imageStacks[i], i, 0);
		}
		
		
		//Sets the whole AnchorPane with elements
		AnchorPane pokerGameView = new AnchorPane(play, gamble, plus, minus, pokerGameCredits, pokerGameBet,
				wintable, cardPane, notification, topLeftImg);
		
		play.setOnAction(e -> {
			for(ImageView v : cardViews) {
				v.setOpacity(1);
			}
			cardPane.setStyle(null);
			if(!gameOn) {
			controller.startPokerGame();
			play.setText("Jako");
			plus.setVisible(false);
			minus.setVisible(false);
			setNotification("Valitse kortit jotka haluat lukita ja paina jako");
			} else {
			setSwappedCards();
			plus.setVisible(true);
			minus.setVisible(true);
			play.setText("Pelaa");
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
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		creditDevelopment = new Tab("Saldo", new Label("Tämä välilehti näyttää saldosi."));
		ImageView growth = new ImageView(new Image("/images/growthtab.png", 25, 22, false, false));
		creditDevelopment.setGraphic(growth);
		combobox = new ComboBox();
		combobox.getItems().add("KAIKKI");
		combobox.getItems().add("Viimeiset 10");
		combobox.getItems().add("Viimeiset 50");
		combobox.getSelectionModel().select(0);
		combobox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           fillStatistics((String) newValue);
	    	}
	    );
		BorderPane creditsPane = new BorderPane();
		creditsPane.setTop(combobox);
		creditsPane.setCenter(lineChart);
		creditDevelopment.setContent(creditsPane);
		
		
		
		
		
		playedGames = new Tab("Pelihistoria"  , new Label("Pelatut pelit"));
        ImageView played = new ImageView(new Image("/images/playedGame.png", 25, 22, false, false));
        playedGames.setGraphic(played);
        
        tableViewGames = new TableView();
        TableColumn<PlayedGame, String> playedIDColumn = new TableColumn<>("Saldo");
        playedIDColumn.setCellValueFactory(new PropertyValueFactory<>("creditAfterPlayer1"));
        TableColumn<PlayedGame, String> playedCreditsColumn = new TableColumn<>("Pelattu");
        playedCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("playedOn"));
        
        tableViewGames.getColumns().add(playedIDColumn);
        tableViewGames.getColumns().add(playedCreditsColumn);
        playedGames.setContent(tableViewGames);
 
        
        
		
        ranking = new Tab("Sijoitukset"  , new Label("Oletko paras?"));
        ImageView rank = new ImageView(new Image("/images/rankingtab.png", 25, 22, false, false));
        ranking.setGraphic(rank);
        tableViewRanks = new TableView();
        TableColumn<PlayerRanking, String> playerRankColumn = new TableColumn<>("Sijoitus");
        playerRankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        TableColumn<PlayerRanking, String> playerCreditsColumn = new TableColumn<>("Saldo");
        playerCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        TableColumn<PlayerRanking, String> playernicknameColumn = new TableColumn<>("Pelaaja");
        playernicknameColumn.setCellValueFactory(new PropertyValueFactory<>("profileName"));
        tableViewRanks.getColumns().add(playerRankColumn);
        tableViewRanks.getColumns().add(playerCreditsColumn);
        tableViewRanks.getColumns().add(playernicknameColumn);
        ranking.setContent(tableViewRanks);
        
        
        tabPane.getTabs().add(ranking);
        tabPane.getTabs().add(creditDevelopment);
        tabPane.getTabs().add(playedGames);
        
        
        creditDevelopment.setDisable(true);
        playedGames.setDisable(true);
        
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
		GridPane rGridPane = new GridPane();
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
		
		rGridPane.add(firstNameRegisterInput, 0, 0);
		rGridPane.add(lastNameRegisterInput, 1, 0);
		rGridPane.add(profileNameRegisterInput, 0, 1);
		rGridPane.add(new Label("Tämä nimi näytetään muille pelaajille"), 1, 1);
		rGridPane.add(emailRegisterInput, 0, 2, 2, 1);
		rGridPane.add(passwordRegisterInput, 0, 3, 2, 1);
		rGridPane.add(passwordRegisterVerifyInput, 0, 4, 2, 1);
		rGridPane.add(creditTransferRegisterInput, 0, 5);
		rGridPane.setPadding(new Insets(10,10,10,10));
		rGridPane.setHgap(5);
		rGridPane.setVgap(10);
		rGridPane.setAlignment(Pos.CENTER);

		rButtons.getChildren().addAll(confirmRegisterButton, cancelRegisterButton);
		rButtons.setPadding(new Insets(10, 10, 10, 10));
		rButtons.setSpacing(10);
		rButtons.setAlignment(Pos.CENTER);

		BorderPane rDialogView = new BorderPane();
		rDialogView.setTop(rHeadline);
		rDialogView.setCenter(rGridPane);
		rDialogView.setBottom(rButtons);
		
        Scene rDialogScene = new Scene(rDialogView); //100,100
        rDialog.setScene(rDialogScene);
        rDialog.show();
        
        //Actions
        //----->
        //These actions have the functionality for the confirm and cancel buttons in Registration window
        //controller handles saving the information to database, it then calls handleRegistrationSuccess method in View
      	confirmRegisterButton.setOnAction(e -> controller.attemptRegistration());
      	//This method closes the window
      	cancelRegisterButton.setOnAction(e -> {
      		Stage stage = (Stage)cancelRegisterButton.getScene().getWindow();
      		stage.close();
      	});
	}
	
	/**
	 * This shows a window showing users their information and lets them change password and user name
	 * @param primaryStage this variable is used to link the new stage to the primaryStage
	 */
	private void showPlayerInfoDialog(Stage primaryStage) {
		Image edit = new Image("/images/edit.png", 20, 20, false, false);
		Image accept = new Image("/images/accept.png", 20, 20, false, false);
		Image cancel = new Image("/images/cancel.png", 20, 20, false, false);
		
		Stage pIDialog = new Stage();
		pIDialog.initModality(Modality.APPLICATION_MODAL);
		pIDialog.initOwner(primaryStage);
		pIDialog.setTitle("Pelaajatiedot");
		VBox pIHeadline = new VBox();
		GridPane pIGridPane = new GridPane();
		HBox pIButtons = new HBox();
		
		Label playerWholeName = new Label(this.player.getFirstName() + " " + this.player.getLastName());
		Label playerJoinDate = new Label(String.valueOf(this.player.getCreatedOn()));
		Label playerEmail = new Label(this.player.getEmail());
		Label playerProfileName = new Label(this.player.getProfileName());
		Label playerPassword = new Label(this.player.getPassword());
		editProfileNameButton = new Button();
		editProfileNameButton.setGraphic(new ImageView(edit));
		editPasswordButton = new Button();
		editPasswordButton.setGraphic(new ImageView(edit));
		savePlayerInfoButton = new Button("Tallenna");
		cancelPlayerInfoButton = new Button("Peruuta");
		
		pIHeadline.getChildren().add(new Label("Pelaajatiedot"));
		pIHeadline.setPadding(new Insets(10, 10, 10, 10));
		pIHeadline.setAlignment(Pos.CENTER);
		
		pIGridPane.add(new Label("Nimi:"), 0, 0);
		pIGridPane.add(playerWholeName, 1, 0);
		pIGridPane.add(new Label("Liittynyt:"), 0, 1);
		pIGridPane.add(playerJoinDate, 1, 1);
		pIGridPane.add(new Label("Sähköposti:"), 0, 2);
		pIGridPane.add(playerEmail, 1, 2);
		pIGridPane.add(new Label("Nimimerkki:"), 0, 3);
		pIGridPane.add(playerProfileName, 1, 3);
		pIGridPane.add(editProfileNameButton, 2, 3);
		pIGridPane.add(new Label("Salasana:"), 0, 4);
		Label pwPlaceholder = new Label("********");
		pIGridPane.add(pwPlaceholder, 1, 4);
		pIGridPane.add(editPasswordButton, 2, 4);
		pIGridPane.setPadding(new Insets(10,10,10,10));
		pIGridPane.setHgap(5);
		pIGridPane.setVgap(5);
		pIGridPane.setAlignment(Pos.CENTER);
		
		pIButtons.getChildren().addAll(savePlayerInfoButton, cancelPlayerInfoButton);
		pIButtons.setPadding(new Insets(10, 10, 10, 10));
		pIButtons.setSpacing(10);
		pIButtons.setAlignment(Pos.CENTER);
		
		BorderPane pIDialogView = new BorderPane();
		pIDialogView.setTop(pIHeadline);
		pIDialogView.setCenter(pIGridPane);
		pIDialogView.setBottom(pIButtons);
		
        Scene pIDialogScene = new Scene(pIDialogView); //100,100
        pIDialog.setScene(pIDialogScene);
        pIDialog.show();
        
        //Actions
        //------>
        //This action opens a TextField where the user can input a new user name. It also opens 2 new actions which are accept and cancel.
        editProfileNameButton.setOnAction(e -> {
        	newProfileNameInput = new TextField();
        	newProfileNameInput.setPromptText("Syötä uusi pelaajanimi");
        	acceptPNEditsButton = new Button();
        	acceptPNEditsButton.setGraphic(new ImageView(accept));
        	cancelPNEditsButton = new Button();
        	cancelPNEditsButton.setGraphic(new ImageView(cancel));
        	pIGridPane.getChildren().remove(playerProfileName);
        	pIGridPane.add(newProfileNameInput, 1, 3);
        	pIGridPane.getChildren().remove(editProfileNameButton);
        	pIGridPane.add(acceptPNEditsButton, 2, 3);
        	pIGridPane.add(cancelPNEditsButton, 3, 3);
        	
        	acceptPNEditsButton.setOnAction(e1 -> {
    			playerProfileName.setText(newProfileNameInput.getText());
    			pIGridPane.getChildren().remove(newProfileNameInput);
    			pIGridPane.getChildren().remove(acceptPNEditsButton);
    			pIGridPane.getChildren().remove(cancelPNEditsButton);
    			pIGridPane.add(playerProfileName, 1, 3);
    			pIGridPane.add(editProfileNameButton, 2, 3);
    		});
        	
    		cancelPNEditsButton.setOnAction(e2 -> {
    			pIGridPane.getChildren().remove(newProfileNameInput);
    			pIGridPane.getChildren().remove(acceptPNEditsButton);
    			pIGridPane.getChildren().remove(cancelPNEditsButton);
    			pIGridPane.add(playerProfileName, 1, 3);
    			pIGridPane.add(editProfileNameButton, 2, 3);
    		});
        });
        
        //This action opens a PasswordField where the user can input a new password. It also opens 2 new actions which are accept and cancel.
		editPasswordButton.setOnAction(e -> {
        	newPasswordInput = new PasswordField();
        	newPasswordInput.setPromptText("Syötä uusi salasana");
        	acceptPSEditsButton = new Button();
        	acceptPSEditsButton.setGraphic(new ImageView(accept));
        	cancelPSEditsButton = new Button();
        	cancelPSEditsButton.setGraphic(new ImageView(cancel));
        	pIGridPane.getChildren().remove(pwPlaceholder);
        	pIGridPane.add(newPasswordInput, 1, 4);
        	pIGridPane.getChildren().remove(editPasswordButton);
        	pIGridPane.add(acceptPSEditsButton, 2, 4);
        	pIGridPane.add(cancelPSEditsButton, 3, 4);
        	
        	acceptPSEditsButton.setOnAction(e1 -> {
        		playerPassword.setText(newPasswordInput.getText());
    			pIGridPane.getChildren().remove(newPasswordInput);
    			pIGridPane.getChildren().remove(acceptPSEditsButton);
    			pIGridPane.getChildren().remove(cancelPSEditsButton);
    			pIGridPane.add(pwPlaceholder, 1, 4);
    			pIGridPane.add(editPasswordButton, 2, 4);
    		});
    		cancelPSEditsButton.setOnAction(e2 -> {
    			pIGridPane.getChildren().remove(newPasswordInput);
    			pIGridPane.getChildren().remove(acceptPSEditsButton);
    			pIGridPane.getChildren().remove(cancelPSEditsButton);
    			pIGridPane.add(pwPlaceholder, 1, 4);
    			pIGridPane.add(editPasswordButton, 2, 4);
    		});
        });
		
		//This method saves the edited information to the current player if the fields are not empty
		savePlayerInfoButton.setOnAction(e -> {
			String newPlayerName = playerProfileName.getText();
			String newPassword = playerPassword.getText();
			if (!newPlayerName.isEmpty() && !newPassword.isEmpty()) {
				this.player.setProfileName(newPlayerName);
				this.player.setPassword(newPassword);
				controller.changePlayerInfo(); //controller handles saving the information to database, it then calls handlePlayerInfoChangeSuccess method in View
			} else {
				showPlayerInfoErrorEmptyFields(); //if the fields are empty, an error message is shown
			}
		});
		
		//This closes the window
		cancelPlayerInfoButton.setOnAction(e -> {
			Stage stage = (Stage)cancelPlayerInfoButton.getScene().getWindow();
			stage.close();
		});
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
		//this is to make sure that the method does not run updateToolBar() and statistics methods before all GUI components are created
		if (creditView != null) {
			updateToolBar();
			try {
				creditDevelopment.setDisable(true);
				creditDevelopment.getContent().setVisible(false);
				playedGames.setDisable(true);
				playedGames.getContent().setVisible(false);
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		System.out.println("Default player set");
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setCurrentPlayer(Player currentPlayer) {
		this.player = currentPlayer;
		updateToolBar();
		setPokerGamePlayerCredits();
		fillStatistics("KAIKKI");
		try {
			creditDevelopment.setDisable(false);
			creditDevelopment.getContent().setVisible(true);
			playedGames.setDisable(false);
			playedGames.getContent().setVisible(true);
		}catch(Exception e) {
			System.out.println(e);
		}
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
		if (this.player.getId() == 1001) { //Checking if the set user is the default player, setting elements in the toolbar accordingly
			registerButton.setVisible(true);
			signInLabel.setVisible(true);
			emailInput.setVisible(true);
			passwordInput.setVisible(true);
			signInButton.setVisible(true);
			playerInfoMI.setVisible(false);
			logOutMI.setVisible(false);
			logInProblemMI.setVisible(true);
		} else {
			registerButton.setVisible(false);
			signInLabel.setVisible(false);
			emailInput.setVisible(false);
			passwordInput.setVisible(false);
			signInButton.setVisible(false);
			playerInfoMI.setVisible(true);
			logOutMI.setVisible(true);
			logInProblemMI.setVisible(false);
		}
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
			fillStatistics((String) combobox.getSelectionModel().getSelectedItem());
		});
		homeButton.setOnAction(e -> {
			mainView.setCenter(mainMenu);
		});
		exitProgram.setOnAction(e -> Platform.exit());
		exitMI.setOnAction(e -> Platform.exit());
		signInButton.setOnAction(e -> controller.attemptLogIn());
		logOutMI.setOnAction(e -> controller.getDefaultPlayer());
		registerButton.setOnAction(e -> showRegisterDialog(primaryStage));
		playerInfoMI.setOnAction(e -> showPlayerInfoDialog(primaryStage));
		infoMI.setOnAction(e -> showProgramInfo());
		logInProblemMI.setOnAction(e -> showPasswordReset());
	}
	
	/**
	 * Custom onClick handler for card image containers. Handles events where card is clicked, places
	 * lock image to container when clicked first time and removes it if repeated.
	 * @param img ImageView image of the card
	 * @param index int card index, helps to determine which card is clicked.
	 * @param pane StackPane card image container.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setImagesOnClick(final StackPane pane, final ImageView img, final int index) {
	    pane.setOnMouseClicked(new EventHandler() {
				@Override
				public void handle(Event event) {
					if(gameOn) {
						if(!cardsToSwapIndexes.contains(index)) {
							cardsToSwapIndexes.add(index);
							lockViews[index].setVisible(false);
							img.setOpacity(1);
						} else {
						cardsToSwapIndexes.remove(Integer.valueOf(index));
						lockViews[index].setVisible(true);
						img.setOpacity(0.5); 
					}
				}
			}
	    });
	}
	
	@Override
	public void setPokerGamePlayerCredits () {
		pokerGameCredits.setText("Saldo: " + Double.toString(this.player.getCredits()));
	}
	
	@Override
	public void setPokerGameBet (double bet) {
		pokerGameBet.setText("Panos: " + Double.toString(bet));
		updateWinTable(bet);
	}
	
	/**
	 * Updates win table texts based on current bet.
	 * @param bet double current bet amount.
	 */
	private void updateWinTable (double bet) {
		//Tänne set textit kokonaisuudessaan plus kerroin ja betti
		winTableTexts.get(0).setText("Ässä pari " + roundToTwoDecimals(bet * HandValue.ACE_PAIR.getMultiplier()));
		winTableTexts.get(1).setText("Kaksi paria " + roundToTwoDecimals(bet * HandValue.TWO_PAIRS.getMultiplier()));
		winTableTexts.get(2).setText("Kolmoset: " + roundToTwoDecimals(bet * HandValue.THREE_OF_A_KIND.getMultiplier()));
		winTableTexts.get(3).setText("Suora " + roundToTwoDecimals(bet * HandValue.STRAIGHT.getMultiplier()));
		winTableTexts.get(4).setText("Väri: " + roundToTwoDecimals(bet * HandValue.FLUSH.getMultiplier()));
		winTableTexts.get(5).setText("Täyskäsi: " + roundToTwoDecimals(bet * HandValue.FULL_HOUSE.getMultiplier()));
		winTableTexts.get(6).setText("Neloset: " + roundToTwoDecimals(bet * HandValue.FOUR_OF_A_KIND.getMultiplier()));
		winTableTexts.get(7).setText("Värisuora: " + roundToTwoDecimals(bet * HandValue.STRAIGHT_FLUSH.getMultiplier()));
	}
	/**
	 * Helper for rounding two decimal places
	 * @param a double value to round
	 * @return double rounded value 
	 */
	private double roundToTwoDecimals (double a) {
		return Math.round(a * 100.0) / 100.0; 
	}
	
	@Override
	public void setCards(ArrayList<String> cards) {
		Platform.runLater(() -> {
		for(int i = 0; i < cards.size(); i++) {
			lockViews[i].setVisible(false);
			Image newCard = new Image("/images/" + cards.get(i) + ".png");
			cardViews[i].setImage(newCard);
			cardViews[i].setOpacity(1);
			setImagesOnClick(imageStacks[i], cardViews[i], i);
		}
		});
	}

	@Override
	public void setScore(String score) {
		if(score == "Ei voittoa") {
			for(ImageView v : cardViews) {
				v.setOpacity(0.5);
			}
		} else {
			cardPane.setStyle("-fx-border-color: #00FF25;" + " -fx-border-width: 5px;");
		}
		setNotification(score);
	}

	/**
	 * This method fills all tabs with data from database.
	 * @param count Value from combobox.
	 */
	private void fillStatistics(String count) {
		
		switch(count) {
		case "KAIKKI":
			lineChart = controller.getLineChart(1000);
			break;
		case "Viimeiset 10":
			lineChart = controller.getLineChart(10);
			break;
		case "Viimeiset 50":
			lineChart = controller.getLineChart(50);
			break;
		}
		
		
		ArrayList<PlayedGame> playedGames = new ArrayList<>();
		playedGames = controller.getPlayedGames();
		
		tableViewGames.getItems().clear();
		for(int i=0; i<playedGames.size();i++) {
			tableViewGames.getItems().add(new PlayedGame(playedGames.get(i).getCreditAfterPlayer1(), playedGames.get(i).getPlayedOn()));
		}
		
		
		ArrayList<Player> ranks = new ArrayList<>();
		ranks = controller.getRanking();
		tableViewRanks.getItems().clear();
		for(int i = 0;i<ranks.size();i++) {
			tableViewRanks.getItems().add(new PlayerRanking(i+1,ranks.get(i).getProfileName(), ranks.get(i).getCredits()));
		}
		
		BorderPane pane = new BorderPane();
		pane.setTop(combobox);
		pane.setCenter(lineChart);
		
		creditDevelopment.setContent(pane);
        ranking.setContent(tableViewRanks);		
	}

	@Override
	public void setSwappedCards() {
		controller.setSwappedCardIndexes(cardsToSwapIndexes);
		cardsToSwapIndexes.clear();
		Collections.addAll(cardsToSwapIndexes,0,1,2,3,4);
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showLogInError() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - syötetty data ei kelpaa");
		alert.setContentText("Varmista että sähköposti ja salasana ovat oikein.");
		alert.showAndWait();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorEmptyFields() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - vaaditut tiedot puuttuvat");
		alert.setContentText("Varmista että etunimi, sukunimi, sähköposti, salasana ja salasanan vahvistus on syötetty.");
		alert.showAndWait();
	}

	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorPasswordsNotMatch() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - salasanat eivät täsmää");
		alert.setContentText("Varmista että syöttämäsi salasanat vastaavat toisiaan.");
		alert.showAndWait();
	}

	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorEmailAlreadyExists() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - sähköpostiosoite on jo käytössä");
		alert.setContentText("Tähän sähköpostiosoitteeseen liitetty tili on jo olemassa.\n\n"
				+ "Kirjaudu sisään tai luo uudet tunnukset käyttäen toista sähköpostiosoitetta.\n"
				+ "Jos et muista salasanaasi, ota yhteyttä tukeen.");
		alert.showAndWait();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void showRegistrationErrorDatabase() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - Tilin luonti ei onnistunut");
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
	public void showPlayerInfoChangeErrorDatabase() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - Pelaajatietojen päivitys ei onnistunut");
		alert.setContentText("Jokin meni pieleen, kokeile hetken kuluttua uudelleen.");
		alert.showAndWait();
	}
	
	/**	{@inheritDoc} */
	@Override
	public void handlePlayerInfoChangeSuccess() {
		Stage stage = (Stage)cancelPlayerInfoButton.getScene().getWindow();
		stage.close();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Pelaajatietojen muuttaminen");
		alert.setHeaderText("Pelaajatietojen muokkaus onnistui!");
		alert.setContentText("Jos vaihdoit salasanasi, muista käyttää uutta salasanaa seuraavalla kirjautumiskerralla");
		alert.showAndWait();
	}
	
	/**
	 * Tells the user that they can't have empty values for user name and password
	 */
	private void showPlayerInfoErrorEmptyFields() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Virhe");
		alert.setHeaderText("Virhe - vaaditut tiedot puuttuvat");
		alert.setContentText("Varmista että pelaajanimi ja salasana eivät ole tyhjiä");
		alert.showAndWait();
	}
	
	/**
	 * Shows user password reset dialog
	 * NO FUNCTIONALITY YET
	 */
	private void showPasswordReset() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Salasanan palauttaminen");
		dialog.setHeaderText("Unohditko salasanasi?\nNoh voi voi, tämä ikkuna ei tee vielä mitään");
		dialog.setContentText("Syötä tilin sähköpostiosoite :");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(email -> System.out.println("Email inputted: " + email)); //to catch the result
	}
	
	/**
	 * Shows user information about the program
	 * NOT MUCH HERE YET
	 */
	private void showProgramInfo() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Tietoa ohjelmasta");
		alert.setHeaderText("Peliymäristö info");
		alert.setContentText(":)");
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
	/**
	 * In pokergame sets up notification and position based on String length
	 * @param text String notification text
	 */
	private void setNotification(String text) {
		if(text.length() < 13) {
		  notification.setLayoutX(370);
		} else if(text.length() >= 13 && text.length() < 30) {
			notification.setLayoutX(310);	
		} else {
			notification.setLayoutX(200);
		}
		notification.setText(text);
	}
	
  @Override
	public void notifyCreditReset(){
    Platform.runLater(() -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("Hävisit pelin!!!");
      alert.setHeaderText("Saldo palautettu, hävisit liikaa");
      alert.setContentText("Opettele pelaamaan");
      alert.showAndWait();
    });
	}
}
