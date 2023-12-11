package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.Misc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboard {
    @FXML
    private AnchorPane childPane;

    @FXML
    void competetionAction(ActionEvent event) throws IOException {
        try{
            Misc.loadAdminCompetetion(childPane);
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }

    @FXML
    void editPersonInfoAction(ActionEvent event) throws IOException {
        try{
        Misc.loadAdminUser(childPane);
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }

    @FXML
    void playerStatsAction(ActionEvent event) throws IOException {
        try{
            Misc.loadAdminPlayerStat(childPane);
        }catch(Exception e){
            System.out.println(e.fillInStackTrace());
        }
    }

    @FXML
    void signOutAction(ActionEvent event) {
        try {
            Misc.loadSignInPage((Stage) this.childPane.getScene().getWindow());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
