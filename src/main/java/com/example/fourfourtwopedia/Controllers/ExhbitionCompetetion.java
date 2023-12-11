package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.DBHandler.MatchDbHandler;
import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Match;
import com.example.fourfourtwopedia.Model.Player;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;

public class ExhbitionCompetetion {
    @FXML
    private TableView<Match> exhibitionMatchTable;

    @FXML
    private ComboBox<String> playerComboBox;

    @FXML
    private TextField playerName;
    private Player player;
    private ArrayList<Player> players;
    TableColumn<Match, Integer> matchNo;

    TableColumn<Match, Button> editButton;
    TableColumn<Match, Integer> awayPlayerScore;
    TableColumn<Match, Integer> homePlayerScore;
    TableColumn<Match, String> awayPlayerName;
    TableColumn<Match, String> homePlayerName;


    @FXML
    void addExhibitionMatch(ActionEvent event) {
        Player selectedPlayer=null;
        if(this.playerComboBox.getValue() == null){
            Misc.ShowErrorAlert("Select any player, player must not be null");
        }
        for (Player p:players) {
            if(p.getUsername().equals(this.playerComboBox.getValue())){
                selectedPlayer=p;
                break;
            }
        }
        Boolean result = MatchDbHandler.registerExhibition(new Match(player,selectedPlayer));
        if(result){
            Misc.ShowInfoAlert("Save exhibition match");
            updateTable();
        }
    }


    private void updateTable() {
        ArrayList<Match> matches = MatchDbHandler.getExhibitionMatchList(player.getUserID());

        if (exhibitionMatchTable.getColumns() != null) {
            exhibitionMatchTable.getColumns().clear();
        }
        // Define table columns
        matchNo = new TableColumn<>("Match No.");
        homePlayerName = new TableColumn<>("Home Player");
        awayPlayerName = new TableColumn<>("Away Player");
        homePlayerScore = new TableColumn<>("Home Player Score");
        awayPlayerScore = new TableColumn<>("Away Player Score");
        editButton = new TableColumn<>("Edit");

        matchNo.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMatchId()).asObject());
        homePlayerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomePlayer().getUsername()));
        awayPlayerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAwayPlayer().getUsername()));


        homePlayerScore.setCellValueFactory(data -> {
            Match match = data.getValue();
            return match.homeUserScoreProperty().asObject();
        });

        awayPlayerScore.setCellValueFactory(data -> {
            Match match = data.getValue();
            return match.awayUserScoreProperty().asObject();
        });

        // Set cell factory to make the cells editable using text fields
        homePlayerScore.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        homePlayerScore.setOnEditCommit(event -> {
            Match match = event.getRowValue();
            match.setHomeUserScore(event.getNewValue());
        });

        awayPlayerScore.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        awayPlayerScore.setOnEditCommit(event -> {
            Match match = event.getRowValue();
            match.setAwayUserScore(event.getNewValue());
        });

        // Implement the logic for the edit button cell value factory
        editButton.setCellFactory(column -> {
            return new TableCell<Match, Button>() {
                private final Button editButton = new Button("Edit");

                {
                    editButton.setOnAction(event -> {
                        Integer index = getIndex();
                        Match match = getTableView().getItems().get(index);
                        if (match != null) {

                            Integer awaySocre = awayPlayerScore.getCellData(index);
                            Integer homeSocre = homePlayerScore.getCellData(index);
                            match.updateScore(homeSocre, awaySocre);
                            updateTable();
                        }
                    });
                }

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };
        });
        exhibitionMatchTable.getColumns().addAll(matchNo, homePlayerName, awayPlayerName, homePlayerScore, awayPlayerScore, editButton);
        exhibitionMatchTable.setEditable(true);
        exhibitionMatchTable.setItems(FXCollections.observableArrayList(matches));
    }
    private void updatePlayerComboBox(){
        this.players=UserDBHandler.getPlayerListWithGuestOne();

        if (this.playerComboBox.getItems() != null) {
            this.playerComboBox.getItems().clear();
        }
        ArrayList<String> playerName = new ArrayList<>();
        for(Player p:players){
            if(p.getUsername().equals(this.player.getUsername())){
                continue;
            }
            playerName.add(p.getUsername());
        }
        playerComboBox.setItems(FXCollections.observableArrayList(playerName));
    }

    @FXML
    public void initialize(Player p) {
        this.player= p;
        updatePlayerComboBox();
        this.playerName.setText(p.getUsername());
        updateTable();
    }
}
