package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Organizer;
import com.example.fourfourtwopedia.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class OrganizerDashboard {
    @FXML
    private Label userNameTxt;

    private Organizer organizer;

    @FXML
    private AnchorPane childPane;


    @FXML
    void competetionAction(ActionEvent event) {
        try {
            Misc.loadCompetetion(childPane,organizer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void editPersonInfoAction(ActionEvent event){
        try {
            Misc.loadUserEditInfo(childPane,organizer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void signOutAction(ActionEvent event) throws IOException {
        Stage stage=(Stage) this.userNameTxt.getScene().getWindow();
        stage.close();
        Misc.loadSignInPage(stage);
    }
    @FXML
    public void initialize(Organizer o) {
      this.organizer= o;
      this.userNameTxt.setText(o.getUsername());
    }
}