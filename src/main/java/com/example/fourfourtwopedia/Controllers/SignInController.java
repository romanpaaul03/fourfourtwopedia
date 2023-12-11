package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import com.example.fourfourtwopedia.Main;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Organizer;
import com.example.fourfourtwopedia.Model.Player;
import com.example.fourfourtwopedia.Model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;

public class SignInController {

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField userPassTxt;

    @FXML
    void closeAction(ActionEvent event) {
        Stage s=(Stage) this.userNameTxt.getScene().getWindow();
        s.close();
    }

    @FXML
    void signInAction(ActionEvent event) {
        // check in the file
        if(userNameTxt.getText().isEmpty()){
            Misc.ShowErrorAlert("User name cannot be empty");
            return;
        }
        if(roleComboBox.getValue()==null){
            Misc.ShowErrorAlert("Select any Role !");
            return;
        }
        if(userPassTxt.getText().isEmpty()){
            Misc.ShowErrorAlert("Password should not be empty");
            return;
        }
        String role=roleComboBox.getValue();
        Stage src;
        switch(role){
            case "Organizer":
                Organizer u = (Organizer) UserDBHandler.loginUser(this.userNameTxt.getText(),this.userPassTxt.getText() ,User.Role.ORGANIZER);
                if(u==null){
                    Misc.ShowErrorAlert("Invalid Credentials");
                    return;
                }
                System.out.println("Found "+ u.getUsername());
                src= (Stage) this.roleComboBox.getScene().getWindow();
                src.close();
                try {
                    Misc.loadOrganizerDashboard(src,u);
                } catch (IOException e) {
                    System.out.println(e.getStackTrace());
                    throw new RuntimeException(e);
                }
                break;
            case "Player":
                Player p = (Player) UserDBHandler.loginUser(this.userNameTxt.getText(),this.userPassTxt.getText(), User.Role.PLAYER);
                if(p==null){
                    Misc.ShowErrorAlert("Invalid Credentials");
                    return;
                }
                src= (Stage) this.roleComboBox.getScene().getWindow();
                System.out.println("Found "+ p.getUsername());
                try {
                    Misc.loadPlayerDashboard(src,p);
                } catch (IOException e) {
                    System.out.println(e.getStackTrace());
                    throw new RuntimeException(e);
                }
                break;
            case "Admin":
                if(userNameTxt.getText().equals("admin") && this.userPassTxt.getText().equals("admin")){
                    System.out.println("Admin Logged in");
                    try {
                        src= (Stage) this.roleComboBox.getScene().getWindow();
                        Misc.loadAdminDashboard(src);
                    } catch (IOException e) {
                        System.out.println(e.getStackTrace());
                        throw new RuntimeException(e);
                    }
                }
                break;
        }
    }

    @FXML
    void signUpAction(MouseEvent event) throws IOException {
        System.out.println("Sign Up clicked");
        Stage stage=(Stage) this.userNameTxt.getScene().getWindow();
        stage.close();
        Misc.loadSignUpPage(stage);
    }
    @FXML
   public void initialize() {
        updateComboBox();
    }
    private void updateComboBox() {
        if (roleComboBox.getItems() != null) {
            this.roleComboBox.getItems().clear();
        }
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Organizer");
        roles.add("Admin");
        roles.add("Player");
        roleComboBox.setItems(FXCollections.observableArrayList(roles));
    }

}
