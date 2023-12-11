package com.example.fourfourtwopedia.Model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;


@SuppressWarnings("unused")
public class PlayerInterface {
	
//	Vector<Match> AllMatches = new Vector<>();
//	Vector<Match> wereAddedWhileRunning = new Vector<>();
//
//	Player Olleito = new Player(1, "Olleito", User.Role.PLAYER);
//	Player LevideWeerd = new Player(2, "LevideWeerd", User.Role.PLAYER);
//	Player EmreYilmaz = new Player(3, "EmreYilmaz", User.Role.PLAYER);
//	Player Mark11 = new Player(4, "Mark11", User.Role.PLAYER);
//	Player Vejrgang = new Player(5, "Vejrgang", User.Role.PLAYER);
//
//	Match Match1 = new Match(1, Olleito, LevideWeerd, 3, 1);
//	Match Match2 = new Match(2, Mark11, Vejrgang, 2, 2);
//	Match Match3 = new Match(3, Olleito, EmreYilmaz, 2, 0);
//    Match Match4 = new Match(4, LevideWeerd, Mark11, 1, 1);
//    Match Match5 = new Match(5, EmreYilmaz, Vejrgang, 0, 3);
//    Match Match6 = new Match(6, Olleito, Mark11, 1, 2);
//    Match Match7 = new Match(7, LevideWeerd, Vejrgang, 2, 1);
//    Match Match8 = new Match(8, EmreYilmaz, Olleito, 1, 1);
//    Match Match9 = new Match(9, Mark11, LevideWeerd, 2, 0);
//    Match Match10 = new Match(10, Vejrgang, EmreYilmaz, 3, 2);
//
//    private void initializeMatches() {
//    	AllMatches.clear();
//        AllMatches.add(Match1);
//        AllMatches.add(Match2);
//        AllMatches.add(Match3);
//        AllMatches.add(Match4);
//        AllMatches.add(Match5);
//        AllMatches.add(Match6);
//        AllMatches.add(Match7);
//        AllMatches.add(Match8);
//        AllMatches.add(Match9);
//        AllMatches.add(Match10);
//
//        for(Match match : wereAddedWhileRunning) {
//        	AllMatches.add(match);
//        }
//    }
//
//    public PlayerInterface() {
//    	initializeMatches();
//    }
//
//	public void showWarningPopUp(String Message) {
//		Alert alert = new Alert(AlertType.WARNING);
//        alert.setTitle("Warning!");
//        alert.setHeaderText(Message);
//        alert.showAndWait();
//	}
//
//	public void switchToHomeScreen(Stage homePage) {
//		AllMatches.clear();
//		initializeMatches();
//
//		VBox lastMatchesBox = new VBox(10);
//		lastMatchesBox.setAlignment(Pos.TOP_LEFT);
//		lastMatchesBox.setPadding(new Insets(50, 0, 0, 0));
//        Label lastMatchesTitleLabel = new Label();
//        lastMatchesTitleLabel.setId("lastMatchesLabel");
//        lastMatchesTitleLabel.setText("Last matches registered");
//
//        lastMatchesBox.getChildren().add(lastMatchesTitleLabel);
//        for (int i = AllMatches.size() - 1; i >= 0; i--) {
//            Match match = AllMatches.get(i);
//            Label toBeAdded = new Label(match.toString());
//            toBeAdded.setId("matchEntryLabel");
//            lastMatchesBox.getChildren().add(toBeAdded);
//        }
//
//
//        VBox Searchbar = new VBox(10);
//        Label SearchForAPlayer = new Label("Type down the username of \n a player to see his stats");
//        Searchbar.setAlignment(Pos.TOP_CENTER);
//        Searchbar.setPadding(new Insets(100, 0, 0, 0));
//        TextField SearchbarTextField = new TextField();
//        SearchbarTextField.setPromptText("Search for a player..");
//        Button SearchButton = new Button();
//        SearchButton.setText("search!");
//        SearchButton.setOnAction(event -> {
//        	String playerSearched = SearchbarTextField.getText();
//
//        	if (playerSearched.equals(Olleito.getUsername())) {
//        	    lastMatchesBox.getChildren().clear();
//        	    switchToPlayerPage(Olleito, homePage);
//        	} else if (playerSearched.equals(LevideWeerd.getUsername())) {
//        	    lastMatchesBox.getChildren().clear();
//        	    switchToPlayerPage(LevideWeerd, homePage);
//        	} else if (playerSearched.equals(EmreYilmaz.getUsername())) {
//        	    lastMatchesBox.getChildren().clear();
//        	    switchToPlayerPage(EmreYilmaz, homePage);
//        	} else if (playerSearched.equals(Mark11.getUsername())) {
//        	    lastMatchesBox.getChildren().clear();
//        	    switchToPlayerPage(Mark11, homePage);
//        	} else if (playerSearched.equals(Vejrgang.getUsername())) {
//        	    lastMatchesBox.getChildren().clear();
//        	    switchToPlayerPage(Vejrgang, homePage);
//        	} else {
//        	    showWarningPopUp("Please search for an existent player!");
//        	}
//
//        });
//
//        Label RegisterNewMatchLabel = new Label("Click the button below \n to register a new match");
//        Button RegisterNewMatchButton = new Button();
//        RegisterNewMatchButton.setText("Register new match");
//        RegisterNewMatchButton.setOnAction(event -> {
//        	switchToRegisterMatch(homePage);
//        });
//
//        Searchbar.getChildren().addAll(SearchForAPlayer, SearchbarTextField, SearchButton, RegisterNewMatchLabel, RegisterNewMatchButton);
//
//        VBox CompetitionsBox = new VBox(10);
//        CompetitionsBox.setAlignment(Pos.TOP_RIGHT);
//		CompetitionsBox.setPadding(new Insets(50, 0, 0, 0));
//        Label ActiveCompetitionsLabel = new Label();
//        ActiveCompetitionsLabel.setText("Active Competitions \n (in development)");
//        ActiveCompetitionsLabel.setId("lastMatchesLabel");
//        CompetitionsBox.getChildren().add(ActiveCompetitionsLabel);
//        for(int i = 0; i < 5; i++) {
//        	Button toBeAdded = new Button();
//        	toBeAdded.setText("Competition" + (i+1));
//        	CompetitionsBox.getChildren().add(toBeAdded);
//        	toBeAdded.setAlignment(Pos.CENTER);
//        }
//
//        HBox homeRoot = new HBox(100);
//        homeRoot.setPadding(new Insets(20));
//        homeRoot.setAlignment(Pos.CENTER);
//        homeRoot.getChildren().addAll(lastMatchesBox, Searchbar, CompetitionsBox);
//        Scene homeScene = new Scene(homeRoot, 1280, 720);
//        homeScene.getStylesheets().add(getClass().getResource("playerinterface.css").toExternalForm());
//
//        homePage.setScene(homeScene);
//        homePage.setTitle("home-page");
//    }
//
//
//	public void switchToPlayerPage(Player player, Stage PlayerPage) {
//
//		player.resetStatistics();
//
//		HBox PostCard = new HBox(20);
//		VBox PlayerDetails = new VBox(0);
//		Label UsernameLabel = new Label("Username: " + player.getUsername());
//		Label PlayerIdLabel = new Label("PlayerID: " + player.getUserID());
//		UsernameLabel.setId("postCardLabel");
//		PlayerIdLabel.setId("postCardLabel");
//		PlayerDetails.getChildren().addAll(UsernameLabel, PlayerIdLabel);
//		PlayerDetails.setAlignment(Pos.CENTER);
//
//		String imagePath = "assets/pictures/userimage.jpg";
//        String absolutePath = new File(imagePath).getAbsolutePath();
//        Image UserImage = new Image("file:" + absolutePath);
//
//        double scalePercentage = 20;
//        double scaledWidth = UserImage.getWidth() * (scalePercentage / 100.0);
//        double scaledHeight = UserImage.getHeight() * (scalePercentage / 100.0);
//        ImageView imageView = new ImageView(UserImage);
//        imageView.setFitWidth(scaledWidth);
//        imageView.setFitHeight(scaledHeight);
//        imageView.setPreserveRatio(true);
//
//        PostCard.setAlignment(Pos.TOP_CENTER);
//		PostCard.getChildren().addAll(imageView, PlayerDetails);
//
//		VBox PlayerStats = new VBox(0);
//		Label PlayerStatsLabel = new Label(player.getUsername() + "'s Statistics");
//		PlayerStatsLabel.setAlignment(Pos.CENTER);
//		PlayerStatsLabel.setStyle(
//		        "-fx-font-size: 25px; " +
//		        "-fx-font-weight: bold; " +
//		        "-fx-text-fill: orange;"
//		);
//		PlayerStats.getChildren().add(PlayerStatsLabel);
//		PlayerStats.setAlignment(Pos.CENTER);
//
//		for(Match match : AllMatches) {
//			if(match.getAwayPlayer().getUsername().equals(player.getUsername())) {
//				player.setMatchesPlayed(player.getMatchesPlayed() + 1);
//				player.setGoalsScored(player.getGoalsScored() + match.getAwayUserScore());
//				player.setGoalsConcedeed(player.getGoalsConcedeed() + match.getHomeUserScore());
//				if(match.getAwayUserScore() > match.getHomeUserScore()) {
//					player.setMatchesWon(player.getMatchesWon() + 1);
//				} else if(match.getAwayUserScore() < match.getHomeUserScore()) {
//					player.setMatchesLost(player.getMatchesLost() + 1);
//				}
//			} else if(match.getHomeUsername().equals(player.getUsername())) {
//				player.setMatchesPlayed(player.getMatchesPlayed() + 1);
//				player.setGoalsScored(player.getGoalsScored() + match.getHomeUserScore());
//				player.setGoalsConcedeed(player.getGoalsConcedeed() + match.getAwayUserScore());
//				if(match.getAwayUserScore() > match.getHomeUserScore()) {
//					player.setMatchesLost(player.getMatchesLost() + 1);
//				} else if(match.getAwayUserScore() < match.getHomeUserScore()) {
//					player.setMatchesWon(player.getMatchesWon() + 1);
//				}
//			}
//		}
//
//		Label MatchesPlayed = new Label("Matches played: " + player.getMatchesPlayed());
//		MatchesPlayed.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//		Label MatchesWon = new Label("Matches won: " + player.getMatchesWon());
//		MatchesWon.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//		Label MatchesLost = new Label("Matches lost: " + player.getMatchesLost());
//		MatchesLost.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//		Label Equals = new Label("Equals: " + (player.getMatchesPlayed() - player.getMatchesWon() - player.getMatchesLost()));
//		Equals.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//		Label GoalsScored = new Label("Goals scored: " + player.getGoalsScored());
//		GoalsScored.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//		Label GoalsConcedeed = new Label("Goals concedeed: " + player.getGoalsConcedeed());
//		GoalsConcedeed.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//		Label GoalsPerMatch = new Label("Goals Per Match: " + ((float) player.getGoalsScored() / player.getMatchesPlayed()));
//		GoalsPerMatch.setStyle(
//				"-fx-font-size: 20px; " +
//				"-fx-font-weight: bold; " );
//
//
//		PlayerStats.getChildren().addAll(MatchesPlayed, MatchesWon, MatchesLost, Equals, GoalsScored, GoalsConcedeed, GoalsPerMatch);
//		Button backToMainMenu = new Button();
//		backToMainMenu.setText("back to the main menu we go!");
//		backToMainMenu.setOnAction(event -> {
//			AllMatches.clear();
//			switchToHomeScreen(PlayerPage);
//		});
//		PlayerStats.getChildren().add(backToMainMenu);
//
//
//		VBox homeRoot = new VBox(20);
//		homeRoot.getChildren().add(PostCard);
//		homeRoot.getChildren().add(PlayerStats);
//        homeRoot.setPadding(new Insets(20));
//        Scene homeScene = new Scene(homeRoot, 1280, 720);
//        homeScene.getStylesheets().add(getClass().getResource("playerinterface.css").toExternalForm());
//
//        PlayerPage.setScene(homeScene);
//        PlayerPage.setTitle("player-page");
//	}
//
//
//	public void switchToRegisterMatch(Stage RegisterMatchStage) {
//	    VBox root = new VBox(20);
//
//	    Label TitleLabel = new Label("Register A Match");
//	    TitleLabel.setStyle(
//	            "-fx-font-size: 25px;" +
//	                    "-fx-font-weight: bold;" +
//	                    "-fx-text-fill: orange;"
//	    );
//
//	    HBox Player1 = new HBox(20);
//	    Label Player1Label = new Label("Player1: ");
//	    TextField Player1Username = new TextField();
//	    Player1Username.setPromptText("First player username");
//	    Label Player1ScoreLabel = new Label("Score: ");
//	    TextField Player1Score = new TextField();
//	    Player1.getChildren().addAll(Player1Label, Player1Username, Player1ScoreLabel, Player1Score);
//	    Player1.setAlignment(Pos.CENTER);
//
//	    HBox Player2 = new HBox(20);
//	    Label Player2Label = new Label("Player2: ");
//	    TextField Player2Username = new TextField();
//	    Player2Username.setPromptText("Second player username");
//	    Label Player2ScoreLabel = new Label("Score: ");
//	    TextField Player2Score = new TextField();
//	    Player2.getChildren().addAll(Player2Label, Player2Username, Player2ScoreLabel, Player2Score);
//	    Player2.setAlignment(Pos.CENTER);
//
//	    HBox guestPlayer2 = new HBox(20);
//	    Label guestPlayer2Label = new Label("Guest Player2? ");
//	    CheckBox guestPlayer2CheckBox = new CheckBox("guest");
//	    guestPlayer2CheckBox.setOnAction(event -> {
//	        Player2Username.setDisable(guestPlayer2CheckBox.isSelected());
//	        if (guestPlayer2CheckBox.isSelected()) {
//	            Player2Username.clear();
//	        }
//	    });
//	    guestPlayer2.getChildren().addAll(guestPlayer2Label, guestPlayer2CheckBox);
//	    guestPlayer2.setAlignment(Pos.CENTER);
//
//	    Button RegisterMatchButton = new Button("Register Match");
//	    RegisterMatchButton.setOnAction(event -> {
//	        try {
//	            String player1Username = Player1Username.getText();
//	            String player2Username;
//	            if (guestPlayer2CheckBox.isSelected()) {
//	                player2Username = "Guest";
//	            } else {
//	                player2Username = Player2Username.getText();
//	            }
//
//	            Integer player1Score = Integer.parseInt(Player1Score.getText());
//	            Integer player2Score = Integer.parseInt(Player2Score.getText());
//
//	            Player player1 = new Player();
//	            Player player2 = new Player();
//	            player1.setUsername(player1Username);
//	            player2.setUsername(player2Username);
//
//	            Match toBeAdded = new Match(AllMatches.size() + 1, player1, player2, player1Score, player2Score);
//	            wereAddedWhileRunning.add(toBeAdded);
//
//	            switchToHomeScreen(RegisterMatchStage);
//	        } catch (NumberFormatException e) {
//	            showWarningPopUp("Please enter valid scores for players.");
//	        }
//	    });
//
//	    root.getChildren().addAll(TitleLabel, Player1, Player2, guestPlayer2, RegisterMatchButton);
//	    root.setAlignment(Pos.CENTER);
//	    Scene homeScene = new Scene(root, 1280, 720);
//	    homeScene.getStylesheets().add(getClass().getResource("playerinterface.css").toExternalForm());
//	    RegisterMatchStage.setScene(homeScene);
//	    RegisterMatchStage.setTitle("Register-new-match");
//	}
}
