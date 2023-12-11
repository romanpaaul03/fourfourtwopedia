package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.Interface.CompetetionListener;
import com.example.fourfourtwopedia.Model.Competition;
import com.example.fourfourtwopedia.Model.PlayerStats;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PlayerStatsController {
    @FXML
    private Label totalDraw;

    @FXML
    private Label totalMatch;

    @FXML
    private Label totalScore;

    @FXML
    private Label winMatches;


    @FXML
    public void initialize(PlayerStats ps) {
        this.totalMatch.setText(ps.getTotalMatchesPlayed().toString());
        this.winMatches.setText(ps.getTotalMatchesWins().toString());
        this.totalDraw.setText(ps.getTotalMatchesDraw().toString());
        this.totalScore.setText(ps.getTotalGoals().toString());
    }
}
