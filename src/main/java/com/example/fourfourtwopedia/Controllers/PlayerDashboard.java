package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.MatchDbHandler;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Organizer;
import com.example.fourfourtwopedia.Model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayerDashboard {
    @FXML
    private Label userNameTxt;

    private Player player;

    @FXML
    private AnchorPane childPane;


    @FXML
    void competetionAction(ActionEvent event) {
        try {
            Misc.loadExibitionCompetetion(childPane,player);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void editPersonInfoAction(ActionEvent event){
        try {
            Misc.loadUserEditInfo(childPane,player);
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
    public void initialize(Player p) {
      this.player= p;
      this.userNameTxt.setText(p.getUsername());
    }

    public void playerStatsOnClick(ActionEvent actionEvent) {
        try{
            Misc.loadPlayerStats(childPane, MatchDbHandler.getPlayerStats(this.player.getUserID()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}