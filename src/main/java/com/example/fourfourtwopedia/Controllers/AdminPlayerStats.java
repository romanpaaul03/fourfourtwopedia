package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.MatchDbHandler;
import com.example.fourfourtwopedia.Model.PlayerStats;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class AdminPlayerStats {
    @FXML
    private TableView<PlayerStats> playerTable;

    @FXML
    public void initialize(){
        ArrayList<PlayerStats> playerStats= MatchDbHandler.getPlayerStats();

        // Set data to the table
        updateTable(playerStats);
    }

    private void updateTable(ArrayList<PlayerStats> playerStats) {
        TableColumn<PlayerStats, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));

        TableColumn<PlayerStats, Integer> matchesPlayedColumn = new TableColumn<>("Total Matches Played");
        matchesPlayedColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalMatchesPlayed()).asObject());

        TableColumn<PlayerStats, Integer> matchesWinsColumn = new TableColumn<>("Total Matches Won");
        matchesWinsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalMatchesWins()).asObject());

        TableColumn<PlayerStats, Integer> matchesDrawColumn = new TableColumn<>("Total Matches Drawn");
        matchesDrawColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalMatchesDraw()).asObject());

        TableColumn<PlayerStats, Integer> goalsColumn = new TableColumn<>("Total Goals");
        goalsColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTotalGoals()).asObject());

        playerTable.getColumns().setAll(usernameColumn, matchesPlayedColumn, matchesWinsColumn, matchesDrawColumn, goalsColumn);

        ObservableList<PlayerStats> playerStatsData = FXCollections.observableArrayList(playerStats);
        playerTable.setItems(playerStatsData);
    }

}
