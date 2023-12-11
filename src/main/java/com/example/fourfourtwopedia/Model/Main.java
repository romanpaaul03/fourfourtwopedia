package com.example.fourfourtwopedia.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class Main extends Application {
	
	public static Connection databaseLink;
	
	public static void databaseConnectionLogic() {
	    	
	    	String databaseName = "proiect_pi_database";
	    	String databaseUser = "root";
	    	String databasePassword = "12345678Pp";
	    	String url = "jdbc:mysql://localhost/" + databaseName;
	    	
	    try {	
	    	Class.forName("com.mysql.cj.jdbc.Driver");
	    	databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
	    	
	        System.out.println("Connection successful");
	    } catch (Exception e) {
	        System.out.println("FATAL ERROR: Couldn't connect to the database!");
	        System.out.println(System.getProperty("java.class.path"));
	        System.out.println(e);
	    }
	}
	    

    @Override
    public void start(Stage welcomeScreen) {
        try {
            Label welcomeLabel = new Label("Welcome to 442pedia!");
            Label welcomeLabel2 = new Label("Enter your data to login or register if you don't have an account");
            welcomeLabel.setId("welcomeLabel");
            welcomeLabel2.setId("welcomeLabel2");
            
            TextField usernameField = new TextField();
            usernameField.setPromptText("username");
            usernameField.setMaxWidth(500);
            PasswordField passwordField = new PasswordField();
            passwordField.setPromptText("password");
            passwordField.setMaxWidth(500);
            
            Button loginButton = new Button();
            loginButton.setText("login");
            Button registerButton = new Button();
            registerButton.setText("register");
            
            HBox buttonBox = new HBox(10);
            buttonBox.getChildren().addAll(loginButton, registerButton);
            buttonBox.setAlignment(Pos.CENTER);
            
            VBox root = new VBox(20);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(-500, 0, 0, 0));
            root.getChildren().addAll(welcomeLabel, welcomeLabel2, usernameField, passwordField, buttonBox);
            
            loginButton.setOnAction(e -> {
                String enteredUsername = usernameField.getText();
                String enteredPassword = passwordField.getText();

                System.out.println("Username: " + enteredUsername);
                System.out.println("Password: " + enteredPassword);
                
                if(enteredUsername.equals("admin") && enteredPassword.equals("admin")) {
                	PlayerInterface PlayerGUI = new PlayerInterface();
//                	PlayerGUI.switchToHomeScreen(welcomeScreen);
                } else {
                	System.out.println("Incorrect login information");
                }
            });
            
            registerButton.setOnAction(e -> {
            	switchToRegisterScreen(welcomeScreen);
            });

            Scene scene = new Scene(root, 1280, 720);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            welcomeScreen.setScene(scene);
            welcomeScreen.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void switchToRegisterScreen(Stage registerScreen) {
    	Label createAccountLabel = new Label("Create an account");
    	createAccountLabel.setId("createAccountLabel");
    	createAccountLabel.setAlignment(Pos.CENTER);
    	
    	TextField usernameRegister = new TextField();
    	usernameRegister.setPromptText("enter your desired username");
    	usernameRegister.setMaxWidth(500);
    	PasswordField passwordRegister = new PasswordField();
    	passwordRegister.setPromptText("enter password");
    	passwordRegister.setMaxWidth(500);
    	PasswordField confirmPasswordRegister = new PasswordField();
    	confirmPasswordRegister.setPromptText("match the password");
    	confirmPasswordRegister.setMaxWidth(500);
    	
    	HBox chooseAccountTypeBox = new HBox(20);
    	chooseAccountTypeBox.setAlignment(Pos.CENTER);
        VBox stackedCheckboxes = new VBox(10);
        Label accountType = new Label();
        accountType.setText("I will be: ");

        CheckBox playerCheckbox = new CheckBox("playing");
        CheckBox organizerCheckbox = new CheckBox("organizing");
        
        playerCheckbox.setOnAction(event -> {
            if (playerCheckbox.isSelected()) {
                organizerCheckbox.setSelected(false);
            }
        });

        organizerCheckbox.setOnAction(event -> {
            if (organizerCheckbox.isSelected()) {
                playerCheckbox.setSelected(false);
            }
        });

        stackedCheckboxes.getChildren().addAll(playerCheckbox, organizerCheckbox);
        chooseAccountTypeBox.getChildren().addAll(accountType, stackedCheckboxes);
    	
    	Button createAccountButton = new Button();
    	createAccountButton.setText("create account!");
    	Button backToWelcomeScreenButton = new Button();
    	backToWelcomeScreenButton.setText("abort mission");
    	
    	HBox buttons = new HBox(20);
    	buttons.setAlignment(Pos.CENTER);
    	buttons.getChildren().addAll(createAccountButton, backToWelcomeScreenButton);
    	
    	VBox root = new VBox(20);
    	root.setAlignment(Pos.CENTER);
    	root.setPadding(new Insets(-200, 0, 0, 0));
    	root.getChildren().addAll(createAccountLabel, usernameRegister, passwordRegister, confirmPasswordRegister, chooseAccountTypeBox, buttons);
    	
    	createAccountButton.setOnAction(e -> {
    		String enteredUsername = usernameRegister.getText();
    		String enteredPassword = passwordRegister.getText();
    		String enteredConfirmPassword = confirmPasswordRegister.getText();
    		
    		System.out.println("Username: " + enteredUsername);
    		System.out.println("Password: " + enteredPassword);
    		System.out.println("ConfirmPassowrd: " + enteredConfirmPassword);
    		
    		if(enteredPassword.equals(enteredConfirmPassword) && !enteredPassword.equals("")) {
    			System.out.println("You could create this account, but this functionality doesn't exist yet :)");
    		} else {
    			System.out.println("Incorrect login info. Match the passwords! (or type one if you didn't)");
    		}
    	});
    	
    	backToWelcomeScreenButton.setOnAction(e -> {
    		start(registerScreen);
    	});
    	
    	Scene scene = new Scene(root, 1280, 720);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        registerScreen.setScene(scene);
        registerScreen.show();
    }

    public static void main(String[] args) {
    	databaseConnectionLogic();
        launch(args);
    }
}
