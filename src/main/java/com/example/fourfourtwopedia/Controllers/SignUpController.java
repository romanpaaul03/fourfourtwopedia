package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SignUpController {
    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField userPass;

    @FXML
    private TextField confirmPassword;

    @FXML
    void backAction(ActionEvent event) throws IOException {
        Stage stage=(Stage) this.roleComboBox.getScene().getWindow();
        stage.close();
        Misc.loadSignInPage(stage);
    }

    @FXML
    void submitAction(ActionEvent event) {
        try{
            if(roleComboBox.getValue() ==null){
                Misc.ShowErrorAlert("Select role !");
                return;
            }
            if(! userPass.getText().equals(this.confirmPassword.getText())){
                Misc.ShowErrorAlert("Password must be same");
                return;
            }
            boolean result=false;
            switch (roleComboBox.getValue()){
                    case "Player":
                        result = UserDBHandler.insertUser(this.userNameTxt.getText(),this.userPass.getText(), User.Role.PLAYER);
                        break;
                    case "Organizer":
                        result=UserDBHandler.insertUser(this.userNameTxt.getText(),this.userPass.getText(), User.Role.ORGANIZER);
                        break;
                }
                if(result){
                    Misc.ShowInfoAlert("Successfully Saved  !");
                    Stage s= (Stage) this.roleComboBox.getScene().getWindow();
                    s.close();
                    Misc.loadSignUpPage(s);
                }
        }
        catch (Exception e){
            Misc.ShowErrorAlert(e.getMessage());
        }

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
        roles.add("Player");
        roleComboBox.setItems(FXCollections.observableArrayList(roles));
    }
}
