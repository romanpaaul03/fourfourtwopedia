package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.Interface.CompetetionListener;
import com.example.fourfourtwopedia.Model.Competition;
import com.example.fourfourtwopedia.Model.Organizer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CompetetionCardController {
    @FXML
    private Label competetionName;
    @FXML
    private Label type;
    private Competition competition;
    private CompetetionListener competetionListener;
    @FXML
    void onClickAction(MouseEvent event) {
        competetionListener.click(competition);
    }

    @FXML
    public void initialize(Competition competition,CompetetionListener competetionListener) {
        this.competition=competition;
        this.competetionName.setText(competition.getCompetitionName());
        this.type.setText(competition.getCompType().toString());
        this.competetionListener=competetionListener;
    }
}
