package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.DBHandler.CompetetionDBHandler;
import com.example.fourfourtwopedia.DBHandler.UserDBHandler;
import com.example.fourfourtwopedia.Interface.CompetetionListener;
import com.example.fourfourtwopedia.Interface.Listener;
import com.example.fourfourtwopedia.Interface.UserListener;
import com.example.fourfourtwopedia.Misc;
import com.example.fourfourtwopedia.Model.Competition;
import com.example.fourfourtwopedia.Model.User;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.TilePane.setMargin;

public class AdminUserController {

    @FXML
    private AnchorPane childPane;

    @FXML
    private GridPane userGridPane;

    private ArrayList<User> users;

    private UserListener myUserListner;
    Listener editReloadListner;

    private void updateCompetitionList(){
        if (this.userGridPane != null) {
            userGridPane.getChildren().clear();
        }
        int row=1;
        int col=0;
        try {
            for (User u : users) {
                AnchorPane content = Misc.getUserCard(u,myUserListner);
                setMargin(content, new Insets(0, 0, 2, 0)); // Sets upper and lower margins to 2px
                content.setMinHeight(45);
                content.setMaxHeight(45);
                userGridPane.add(content, col, row);
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void initialize() {
        users = UserDBHandler.getUsers();
        myUserListner = new UserListener() {
            @Override
            public void click(User user) {
                try{
                    Misc.loadUserEditInfo(childPane,user);
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
