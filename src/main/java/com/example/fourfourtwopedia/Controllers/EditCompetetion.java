package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.DBHandler.MatchDbHandler;
import com.example.fourfourtwopedia.Interface.Listener;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Competition;
import com.example.fourfourtwopedia.Model.Match;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class EditCompetetion {
    @FXML
    private TextField competitionName;
    private Competition competition;
    @FXML
    private TableView<Match> matchesTable;
    private Stage primaryStage;
    private Listener myListener;
    @FXML
    TableColumn<Match, Integer> matchNo;
//    @FXML
    TableColumn<Match, Button> editButton;

    TableColumn<Match, Integer> awayPlayerScore;
    TableColumn<Match, Integer> homePlayerScore;
    TableColumn<Match, String> awayPlayerName;
    TableColumn<Match, String> homePlayerName;


    @FXML
    void saveCompetetionAction(ActionEvent event) {
        if(competitionName.getText().isEmpty()){
            Misc.ShowErrorAlert("Name Should not be Empty");
            return;
        }
        try{
            CompetetionDBHandler.updateCompetetionName(competition.getCompetitionID(),this.competitionName.getText());
            competition=CompetetionDBHandler.getCompetetion(competition.getCompetitionID());
            myListener.click("Null");
            Misc.ShowErrorAlert("Successfully Updated");
        }
        catch (Exception e){
            System.out.println(e.fillInStackTrace());
            Misc.ShowErrorAlert(e.getMessage());
        }
    }
    @FXML
    public void initialize(Competition competetion,Listener myListener,Stage primaryStage) {
        this.competition=competetion;
        this.competitionName.setText(competetion.getCompetitionName());
        updateMatches();
        this.primaryStage=primaryStage;
        this.myListener=myListener;
    }

    private void updateMatches() {
        if(matchesTable.getColumns()!=null){
            matchesTable.getColumns().clear();
        }
        // Define table columns
        matchNo = new TableColumn<>("Match No.");
        homePlayerName = new TableColumn<>("Home Player");
        awayPlayerName = new TableColumn<>("Away Player");
        homePlayerScore = new TableColumn<>("Home Player Score");
        awayPlayerScore = new TableColumn<>("Away Player Score");
        editButton = new TableColumn<>("Edit");

        // Set cell value factories to populate table columns
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
                        Integer index=getIndex();
                        Match match = getTableView().getItems().get(index);
                        if (match != null) {
                            if(competition.getCompType()== Competition.CompetitionType.KNOCKOUT){
                                if(! MatchDbHandler.isEditableMatch(match.getMatchId(),competition.getCompetitionID())){
                                    Misc.ShowErrorAlert("Cannot Edit the Score");
                                    return;
                                }
                            }
                            Integer awaySocre = awayPlayerScore.getCellData(index);
                            Integer homeSocre = homePlayerScore.getCellData(index);
                            match.updateScore(homeSocre,awaySocre);
                            if(competition.getCompType()== Competition.CompetitionType.KNOCKOUT){
                                try {
                                    competition.checkNewMatch();
                                } catch (Exception e) {
                                    System.out.println(e.fillInStackTrace());
                                    if(competition.isLastMatc(match.getMatchId())){
                                        Misc.ShowErrorAlert(e.getMessage());
                                    }
                                }
                            }
                            updateMatches();
                        }
                    });
                    competition = CompetetionDBHandler.getCompetetion(competition.getCompetitionID());
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



        // Add defined columns to the TableView
        matchesTable.getColumns().addAll(matchNo, homePlayerName, awayPlayerName, homePlayerScore, awayPlayerScore, editButton);
        matchesTable.setEditable(true);
        matchesTable.setItems(FXCollections.observableArrayList(this.competition.getMatches()));
    }

    public void deleteCompetetionAction(ActionEvent actionEvent) {
        try{
        CompetetionDBHandler.updateCompetetionActiveStatus(competition.getCompetitionID(),false);
        myListener.click("Null");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
