package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import com.example.fourfourtwopedia.Interface.Listener;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Competition;
import com.example.fourfourtwopedia.Model.Organizer;
import com.example.fourfourtwopedia.Model.Player;
import com.example.fourfourtwopedia.Model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class AddCompetetion {

    @FXML
    private ComboBox<String> competetionType;

    @FXML
    private TextField competitionName;

    @FXML
    private ComboBox<String> playerComboBox;

    @FXML
    private GridPane playersGridPane;
    private Organizer organizer;

    private ArrayList<Player> selectedPlayers = new ArrayList<>();
    private Listener myDeleteListner;

    @FXML
    void addPlayerAction(ActionEvent event) {
        if(playerComboBox.getValue()==null){
            Misc.ShowErrorAlert("Select the Player");
            return;
        }

        selectedPlayers.add( (Player) UserDBHandler.getUser(playerComboBox.getValue(), User.Role.PLAYER));
        updatePlayersComboBox();
        updatePlayerGrid();
    }

    private void updatePlayerGrid() {
        if (this.playersGridPane != null) {
            playersGridPane.getChildren().clear();
        }
        int row=1;
        int col=0;
        try {
            for (Player player : selectedPlayers) {
                AnchorPane content = Misc.getPlayerCard(player,myDeleteListner);
                if (col == 4) {
                    col = 0;
                    row++;
                }

                playersGridPane.add(content, col, row);
                col++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveCompetetionAction(ActionEvent event) {
        Integer[] playerNum={2,4,8,16};
        boolean isPresent=false;
        for (Integer i:playerNum   ) {
            if(i == selectedPlayers.size())
            {
                isPresent=true;
                break;
            }
        }
        if(isPresent ==false){
            Misc.ShowErrorAlert("Invalid value for NumberOfPlayers");
            return;
        }
        if(competetionType.getValue()==null){
            Misc.ShowErrorAlert("Select any competition type");
            return;
        }
        if(competitionName.getText().isEmpty()){
            Misc.ShowErrorAlert("Competition name should not be empty");
            return;
        }
        try{
            Boolean result = organizer.registerCompetetion(competitionName.getText(),selectedPlayers,competetionType.getValue().equals("League") ? Competition.CompetitionType.LEAGUE : Competition.CompetitionType.KNOCKOUT);
            if(result){
                Misc.ShowInfoAlert("Save Competition Details");
                this.competitionName.setText("");
                this.selectedPlayers=null;
                updatePlayerGrid();
            }
        }
        catch (Exception e){
           System.out.println(e.fillInStackTrace());
           throw e;
        }
    }
    @FXML
    public void initialize(Organizer o) {
        this.organizer=o;
        updateComboBox();
        updatePlayersComboBox();
        myDeleteListner=new Listener() {
            @Override
            public void click(String playerName) {
                int i=0;
                for (Player p:selectedPlayers) {
                    if(p.getUsername().equals(playerName)){
                        break;
                    }
                    i++;
                }
                selectedPlayers.remove(i);
                updatePlayerGrid();
            }
        };
    }
    private boolean isSelected(Player p){
        for (Player player:selectedPlayers ) {
            if(player.getUserID()==p.getUserID()){
                return true;
            }
        }
        return false;
    }
    private void updatePlayersComboBox() {
        ArrayList<String> names = new ArrayList<>();
        for (Player p: UserDBHandler.getPlayerList()) {
            if(!isSelected(p)){names.add(p.getUsername());}
        }
        playerComboBox.setItems(FXCollections.observableArrayList(names));
    }

    private void updateComboBox() {
        if (competetionType.getItems() != null) {
            this.competetionType.getItems().clear();
        }
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Knockout");
        roles.add("League");
        competetionType.setItems(FXCollections.observableArrayList(roles));
    }
}