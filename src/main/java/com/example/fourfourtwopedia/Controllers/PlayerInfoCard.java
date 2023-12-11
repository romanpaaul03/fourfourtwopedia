package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.Interface.Listener;
import com.example.fourfourtwopedia.Model.Organizer;
import com.example.fourfourtwopedia.Model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class PlayerInfoCard {

    @FXML
    private Label nameLbl;
    private Listener myDelListner;
    @FXML
    void deleteClick(MouseEvent event) throws IOException {
        myDelListner.click(this.nameLbl.getText());
    }
    @FXML
    public void initialize(Player p,Listener myDelListner) {
        this.nameLbl.setText(p.getUsername());
       this.myDelListner=myDelListner;
    }
}
