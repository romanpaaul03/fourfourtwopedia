package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class EditUserInfo {

    @FXML
    private TextField userNameTxt;
    @FXML
    private TextField userPassTxt;
    private User user;

    @FXML
    void saveCompetetionAction(ActionEvent event) {
        if(this.userNameTxt.getText().isEmpty()){
            Misc.ShowErrorAlert("User Name should not be empty");
            return;
        }
        if(userPassTxt.getText().isEmpty()){
            Misc.ShowErrorAlert("Password should not be empty");
            return;
        }
        UserDBHandler.updateUserName(user.getUserID(),userNameTxt.getText(),this.userPassTxt.getText());
    }
    @FXML
    public void initialize(User user) {
        this.user=user;
        this.userNameTxt.setText(user.getUsername());
        this.userPassTxt.setText(user.getPassword());
    }
}