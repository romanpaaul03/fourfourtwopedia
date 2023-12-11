package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.Interface.CompetetionListener;
import com.example.fourfourtwopedia.Interface.Listener;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Competition;
import com.example.fourfourtwopedia.Model.Organizer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.TilePane.setMargin;

public class AdminCompetetion {
    @FXML
    private AnchorPane childPane;

    @FXML
    private GridPane competetionGridPane;

    private ArrayList<Competition> competitions;

    private CompetetionListener myCompetitionListner;
        Listener editReloadListner;

    private void updateCompetitionList(){
        if (this.competetionGridPane != null) {
            competetionGridPane.getChildren().clear();
        }
        int row=1;
        int col=0;
        try {
            for (Competition competition : competitions) {
                AnchorPane content = Misc.getCompetetionCard(competition,myCompetitionListner);
                setMargin(content, new Insets(0, 0, 2, 0)); // Sets upper and lower margins to 2px
                content.setMinHeight(45);
                content.setMaxHeight(45);
                competetionGridPane.add(content, col, row);
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void initialize() {
        competitions=CompetetionDBHandler.getCompetitions();
        myCompetitionListner=new CompetetionListener() {
            @Override
            public void click(Competition competition) {
                try{
                    Misc.loadEditCompetetion(childPane,(Stage) childPane.getScene().getWindow(),editReloadListner,competition);
                }
                catch (Exception e){
                    System.out.println(e.fillInStackTrace());
                }
            }
        };
        editReloadListner=new Listener() {
            @Override
            public void click(String playerName) throws IOException {
                initialize();
            }
        };
        updateCompetitionList();
    }
}
