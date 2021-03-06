package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.PlayedGame;
import model.Player;
import model.PlayerRanking;

/**
 * The Graphical User Interface built with JavaFX
 * @author ---
 * @version 1.5 03.03.2021
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
	private ArrayList<ImageView> pokerGameCardImgs;
	private HashMap<Integer, Image> clickedImgs = new HashMap <Integer, Image>();
	private GridPane cardPane;
	private ArrayList<Integer> cardsToSwapIndexes = new ArrayList <Integer>();
	private boolean gameOn = false;
	private Text notification;

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
	
	//playerinformation components
	private Button savePlayerInfoButton;
	private Button cancelPlayerInfoButton;


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
		playerInfoMI = new MenuItem("Näytä pelaajatiedot");
		logOutMI = new MenuItem("Kirjaudu ulos");
		logOutMI.setVisible(false);
		playerMenu.getItems().addAll(playerInfoMI, logOutMI);
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
		
		notification = new Text("Valitse panos ja paina jako");
		AnchorPane.setBottomAnchor(notification, 70.0);
		notification.setLayoutX(270);
		//AnchorPane.setLeftAnchor(notification, 350.0);
		notification.setFont(Font.font(24));
		
		// gamble button placement
		Button gamble = new Button ("Tuplaa");
		gamble.setLayoutX(367.0);
		gamble.setLayoutY(330.0);
		gamble.setPrefHeight(58.0);
		gamble.setPrefWidth(98.0);
		AnchorPane.setBottomAnchor(gamble, 11.39);
		AnchorPane.setRightAnchor(gamble, 135.0);
		
		// play button placement
		Button play = new Button("Jako");
		//play.setStyle("-fx-background-color: green");
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
		
		for(int i = 0; i < 3; i++) {
			ColumnConstraints column = new ColumnConstraints();
			column.setPrefWidth(100.0);
			wintable.getColumnConstraints().add(column);
		}
		
		Text acepair = new Text("Ässä pari:");
		Text pair = new Text("Kaksi paria:");
		Text threeofkind = new Text("Kolmoset:");
		Text straight = new Text("Suora:");
		Text flush = new Text("Väri:");
		Text fullhouse = new Text("Täyskäsi:");
		Text fourofkind = new Text("Neloset:");
		Text straightflush = new Text("Värisuora:");
		wintable.add(acepair, 0, 2);
		wintable.add(pair, 1, 2);
		wintable.add(threeofkind, 2, 2);
		wintable.add(straight, 0, 1);
		wintable.add(flush, 1, 1);
		wintable.add(fullhouse, 2, 1);
		wintable.add(fourofkind, 0, 0);
		wintable.add(straightflush, 1, 0);
		
		//wintable.add(pair, 0, 3);
		//wintable.add(threeofkind, 0, 2);
		//wintable.add(straight, 0, 1);
		//wintable.add(flush, 0, 0);
		//wintable.add(fullhouse, 1, 3);
		//wintable.add(fourofkind, 1, 2);
		//wintable.add(straightflush, 1, 1);
		
		// Gridpane for card images. Below space for ''locked''.
		cardPane = new GridPane ();
		cardPane.setLayoutX(8.0);
		cardPane.setLayoutY(158.0);
		cardPane.setPrefHeight(166.0);
		cardPane.setPrefWidth(579.0);
		AnchorPane.setTopAnchor(cardPane, 115.0);
		AnchorPane.setRightAnchor(cardPane, 10.0);
		AnchorPane.setLeftAnchor(cardPane, 10.0);
		AnchorPane.setBottomAnchor(cardPane, 105.0);
		
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
		AnchorPane pokerGameView = new AnchorPane(play, gamble, plus, minus, pokerGameCredits, pokerGameBet, wintable, cardPane, notification);
		//pokerGameView.setPrefSize(600, 400);
		
		play.setOnAction(e -> {
			if(!gameOn) {
			controller.startPokerGame();
			plus.setVisible(false);
			minus.setVisible(false);
			setNotification("Valitse kortit jotka haluat vaihtaa ja paina jako");
			} else {
			setSwappedCards();
			plus.setVisible(true);
			minus.setVisible(true);
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
		
		creditDevelopment = new Tab("Credits", new Label("This pane shows your credit development from the beginning"));
		ImageView growth = new ImageView(new Image("/images/growthtab.png", 25, 22, false, false));
		creditDevelopment.setGraphic(growth);
		combobox = new ComboBox();
		combobox.getItems().add("MAX");
		combobox.getItems().add("Last 10");
		combobox.getItems().add("Last 50");
		combobox.getSelectionModel().select(0);
		combobox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
	           //System.out.println(newValue);
	           fillStatistics((String) newValue);
	    	}
	    );
		BorderPane creditsPane = new BorderPane();
		creditsPane.setTop(combobox);
		creditsPane.setCenter(lineChart);
		creditDevelopment.setContent(creditsPane);
		
		
		
		
		
		playedGames = new Tab("Game history"  , new Label("Played games"));
        ImageView played = new ImageView(new Image("/images/playedGame.png", 25, 22, false, false));
        playedGames.setGraphic(played);
        
        tableViewGames = new TableView();
        TableColumn<PlayedGame, String> playedIDColumn = new TableColumn<>("Credits");
        playedIDColumn.setCellValueFactory(new PropertyValueFactory<>("creditAfterPlayer1"));
        TableColumn<PlayedGame, String> playedCreditsColumn = new TableColumn<>("Played on");
        playedCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("playedOn"));
        
        tableViewGames.getColumns().add(playedIDColumn);
        tableViewGames.getColumns().add(playedCreditsColumn);
        /*
        tableViewGames.getItems().add(new PlayedGame(100, new Date()));
        tableViewGames.getItems().add(new PlayedGame(125,new Date()));
        */
        playedGames.setContent(tableViewGames);
 
        
        
		
        ranking = new Tab("Ranking"  , new Label("Can you beat the best players?"));
        ImageView rank = new ImageView(new Image("/images/rankingtab.png", 25, 22, false, false));
        ranking.setGraphic(rank);
        tableViewRanks = new TableView();
        TableColumn<PlayerRanking, String> playerRankColumn = new TableColumn<>("Rank");
        playerRankColumn.setCellValueFactory(new PropertyValueFactory<>("rank"));
        TableColumn<PlayerRanking, String> playerCreditsColumn = new TableColumn<>("Credits");
        playerCreditsColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));
        TableColumn<PlayerRanking, String> playerfnColumn = new TableColumn<>("First name");
        playerfnColumn.setCellValueFactory(new PropertyValueFactory<>("fn"));
        TableColumn<PlayerRanking, String> playerlnColumn = new TableColumn<>("Last name");
        playerlnColumn.setCellValueFactory(new PropertyValueFactory<>("ln"));
        tableViewRanks.getColumns().add(playerRankColumn);
        tableViewRanks.getColumns().add(playerCreditsColumn);
        tableViewRanks.getColumns().add(playerfnColumn);
        tableViewRanks.getColumns().add(playerlnColumn);
        ranking.setContent(tableViewRanks);
        
        
        
        tabPane.getTabs().add(creditDevelopment);
        tabPane.getTabs().add(playedGames);
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
		rGridPane.setVgap(5);
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
        createRegisterActions();
        rDialog.show();
	}
	
	/**
	 * This shows a window showing users their information and lets them change password and user name
	 * @param primaryStage this variable is used to link the new stage to the primaryStage
	 */
	private void showPlayerInfoDialog(Stage primaryStage) {
		Image edit = new Image("/images/edit.png", 20, 20, false, false);
		
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
		Button editProfileNameButton = new Button();
		editProfileNameButton.setGraphic(new ImageView(edit));
		Button editPasswordButton = new Button();
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
		pIGridPane.add(new Label("********"), 1, 4);
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
        createPlayerInfoActions();
        pIDialog.show();
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
			logOutMI.setVisible(false);
			updateToolBar();
		}
		System.out.println("Default player set");
	}
	
	/**	{@inheritDoc} */
	@Override
	public void setCurrentPlayer(Player currentPlayer) {
		this.player = currentPlayer;
		logOutMI.setVisible(true);
		updateToolBar();
		setPokerGamePlayerCredits();
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
			fillStatistics((String) combobox.getSelectionModel().getSelectedItem());
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
		playerInfoMI.setOnAction(e -> {
			if (this.player.getId() == 1001) { //if the current player is Tester, it means he/she has not logged in yet
				showPlayerInfoErrorNotLoggedIn(); //User is prompted to log in before they can see their information
			} else {
				showPlayerInfoDialog(primaryStage); 
			}
		});
		infoMI.setOnAction(e -> showProgramInfo());
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
	 * This method has the functionality for the save and cancel buttons in Player info window
	 * SAVE METHOD NOT IMPLEMENTED YET
	 */
	private void createPlayerInfoActions() {
		//savePlayerInfoButton, 
		cancelPlayerInfoButton.setOnAction(e -> {
			Stage stage = (Stage)cancelPlayerInfoButton.getScene().getWindow();
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
					if(gameOn) {
					if(cardsToSwapIndexes.contains(index)) {
						img.setImage(clickedImgs.get(index));
						clickedImgs.remove(index);
						cardsToSwapIndexes.remove(Integer.valueOf(index));
					} else {
						clickedImgs.put(index, img.getImage());
						img.setImage(new Image("/images/green_back.png"));
						cardsToSwapIndexes.add(index);
						}
					}
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
		setNotification(score);
	}

	private void fillStatistics(String count) {
		
		switch(count) {
		case "MAX":
			lineChart = controller.getLineChart(1000);
			break;
		case "Last 10":
			lineChart = controller.getLineChart(10);
			break;
		case "Last 50":
			lineChart = controller.getLineChart(50);
			break;
		}
		
		
		ArrayList<PlayedGame> playedGames = new ArrayList<>();
		playedGames = controller.getPlayedGames();
		//System.out.println("TESTETSETESTEST "+playedGames.get(0).getCreditAfterPlayer1());
		
		tableViewGames.getItems().clear();
		for(int i=0; i<playedGames.size();i++) {
			tableViewGames.getItems().add(new PlayedGame(playedGames.get(i).getCreditAfterPlayer1(), playedGames.get(i).getPlayedOn()));
		}
		
		
		
		
		ArrayList<Player> ranks = new ArrayList<>();
		ranks = controller.getRanking();
		tableViewRanks.getItems().clear();
		for(int i = 0;i<ranks.size();i++) {
			tableViewRanks.getItems().add(new PlayerRanking(i+1,ranks.get(i).getFirstName(), 
					ranks.get(i).getLastName(), ranks.get(i).getCredits()));
		}
		/*
		tableViewRanks = new TableView();
		String[] ranks = controller.getRanking();
		for(int i = 0;i<ranks.length;i++) {
			tableViewRanks.getItems().add(ranks[i]);
		}
		*/
		
		
		BorderPane pane = new BorderPane();
		//creditDevelopment.setContent(value);
		pane.setTop(combobox);
		pane.setCenter(lineChart);
		
		creditDevelopment.setContent(pane);
        ranking.setContent(tableViewRanks);		
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
	
	/**
	 * Tells the user that they need to be logged in to view their information
	 */
	private void showPlayerInfoErrorNotLoggedIn() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Huomio");
		alert.setHeaderText("Huomio - Et ole kirjautunut sisään");
		alert.setContentText("Olet tällä hetkellä testaaja\nKirjaudu sisään tai luo tili tarkastellaksesi pelaajatietoja");
		alert.showAndWait();
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
	
	public void setNotification(String text) {
		if(text.length() < 13) {
		  notification.setLayoutX(360);
		} else if(text.length() >= 13 && text.length() < 30) {
			notification.setLayoutX(290);	
		} else {
			notification.setLayoutX(180);
		}
		notification.setText(text);
	}
}
