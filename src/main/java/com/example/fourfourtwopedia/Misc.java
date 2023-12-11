package com.example.fourfourtwopedia;

import com.example.fourfourtwopedia.Controllers.*;
import com.example.fourfourtwopedia.Interface.CompetetionListener;
import com.example.fourfourtwopedia.Interface.Listener;
import com.example.fourfourtwopedia.Interface.UserListener;
import com.example.fourfourtwopedia.Model.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Misc {
    public static void ShowInfoAlert(String message){
        Alert a =new Alert(Alert.AlertType.INFORMATION,message);
        a.showAndWait();

    }
    public static void ShowErrorAlert(String message){
        Alert a =new Alert(Alert.AlertType.INFORMATION,message);
        a.showAndWait();
    }
    public static void loadSignUpPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    public static void loadSignInPage(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

public static void loadOrganizerDashboard(Stage stage, Organizer organizer) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("OrganizerDashboard.fxml"));
    AnchorPane content = fxmlLoader.load();
    OrganizerDashboard orgDash = fxmlLoader.getController();
    orgDash.initialize(organizer);
    Scene scene = new Scene(content);
    stage.setScene(scene);

    // Set up dragging for the stage
    Double[] dragDelta = new Double[2];
    content.setOnMousePressed(event -> {
        dragDelta[0] = stage.getX() - event.getScreenX();
        dragDelta[1] = stage.getY() - event.getScreenY();
    });

    content.setOnMouseDragged(event -> {
        stage.setX(event.getScreenX() + dragDelta[0]);
        stage.setY(event.getScreenY() + dragDelta[1]);
    });

    // Center the stage
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - stage.getWidth()) / 2;
    double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - stage.getHeight()) / 2;
    stage.setX(centerX);
    stage.setY(centerY);

    stage.show();
}

    public static void loadCompetetion(AnchorPane parent,Organizer o) throws IOException {
        // Clear the parent AnchorPane's children and add the 'childPane'
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("Competetion.fxml"));
        AnchorPane content=fxmlLoader.load();
        CompetitionController competetionController = fxmlLoader.getController();

        competetionController.initialize(o);
        parent.getChildren().clear();
        parent.getChildren().add(content);
        // Set the AnchorPane constraints for 'childPane' (optional)
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }
    public static void loadAddCompetetion(AnchorPane parent,Organizer o) throws IOException {
            // Clear the parent AnchorPane's children and add the 'childPane'
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("AddCompetetion.fxml"));
        AnchorPane content=fxmlLoader.load();
        AddCompetetion addCompetetion = fxmlLoader.getController();

        addCompetetion.initialize(o);
        parent.getChildren().clear();
        parent.getChildren().add(content);
        // Set the AnchorPane constraints for 'childPane' (optional)
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }
    public static AnchorPane getPlayerCard(Player player, Listener deleteListner) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Misc.class.getResource("PlayerInfoCard.fxml"));
        AnchorPane content=fxmlLoader.load();
        PlayerInfoCard playerInfoCard=fxmlLoader.getController();
        playerInfoCard.initialize(player,deleteListner);
        return content;
    }
    public static AnchorPane getCompetetionCard(Competition competition, CompetetionListener clickListener) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Misc.class.getResource("CompetetionCard.fxml"));
        AnchorPane content=fxmlLoader.load();
        CompetetionCardController competetionCardController=fxmlLoader.getController();
        competetionCardController.initialize(competition,clickListener);
        return content;
    }
    public static AnchorPane getUserCard(User competition, UserListener clickListener) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Misc.class.getResource("UserCard.fxml"));
        AnchorPane content=fxmlLoader.load();
        UserCardController competetionCardController=fxmlLoader.getController();
        competetionCardController.initialize(competition,clickListener);
        return content;
    }
    public static void loadEditCompetetion(AnchorPane anchorPane,Stage stage,Listener reloadListner,Competition competition) throws IOException {
        // Clear the parent AnchorPane's children and add the 'childPane'
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("EditCompetetion.fxml"));
        AnchorPane content=fxmlLoader.load();
        EditCompetetion editCompetetionController = fxmlLoader.getController();

        editCompetetionController.initialize(competition,reloadListner,stage);
        anchorPane.getChildren().clear();
        anchorPane.getChildren().add(content);
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }

    public static void loadUserEditInfo(AnchorPane childPane, User organizer) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("EditUserInfo.fxml"));
        AnchorPane content=fxmlLoader.load();
        EditUserInfo editUserInfo = fxmlLoader.getController();

        editUserInfo.initialize(organizer);
        childPane.getChildren().clear();
        childPane.getChildren().add(content);
        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }


    public static void loadExibitionCompetetion(AnchorPane parent,Player player) throws IOException {
        // Clear the parent AnchorPane's children and add the 'childPane'
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("ExhibitionCompetetion.fxml"));
        AnchorPane content=fxmlLoader.load();
        ExhbitionCompetetion competetionController = fxmlLoader.getController();

        competetionController.initialize(player);
        parent.getChildren().clear();
        parent.getChildren().add(content);

        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }

    public static void loadPlayerDashboard(Stage stage, Player p) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("PlayerDashboard.fxml"));
            AnchorPane content=fxmlLoader.load();
            PlayerDashboard playerDash = fxmlLoader.getController();
            playerDash.initialize(p);
            Scene scene = new Scene(content);
            stage.setScene(scene);

        // Set up dragging for the stage
        Double[] dragDelta = new Double[2];
        content.setOnMousePressed(event -> {
            dragDelta[0] = stage.getX() - event.getScreenX();
            dragDelta[1] = stage.getY() - event.getScreenY();
        });

        content.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + dragDelta[0]);
            stage.setY(event.getScreenY() + dragDelta[1]);
        });


        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - stage.getWidth()) / 2;
            double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - stage.getHeight()) / 2;

            // Set the stage position to the center
            stage.setX(centerX);
            stage.setY(centerY);
            stage.show();

    }


    public static void loadPlayerStats(AnchorPane childPane, PlayerStats ps) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("PlayerStats.fxml"));
        AnchorPane content=fxmlLoader.load();
        PlayerStatsController playerStatsDash = fxmlLoader.getController();
        playerStatsDash.initialize(ps);


        childPane.getChildren().clear();
        childPane.getChildren().add(content);

        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }

    public static void loadAdminDashboard(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("AdminDashboard.fxml"));
        AnchorPane content=fxmlLoader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);

        // Set up dragging for the stage
        Double[] dragDelta = new Double[2];
        content.setOnMousePressed(event -> {
            dragDelta[0] = stage.getX() - event.getScreenX();
            dragDelta[1] = stage.getY() - event.getScreenY();
        });

        content.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + dragDelta[0]);
            stage.setY(event.getScreenY() + dragDelta[1]);
        });


        // Calculate the center position of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - stage.getWidth()) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - stage.getHeight()) / 2;

        // Set the stage position to the center
        stage.setX(centerX);
        stage.setY(centerY);
        stage.show();
    }

    public static void loadAdminCompetetion(AnchorPane childPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("AdminCompetition.fxml"));
        AnchorPane content=fxmlLoader.load();
        AdminCompetetion playerStatsDash = fxmlLoader.getController();
        playerStatsDash.initialize();


        childPane.getChildren().clear();
        childPane.getChildren().add(content);

        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }
    public static void loadAdminUser(AnchorPane childPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("AdminUser.fxml"));
        AnchorPane content=fxmlLoader.load();
        AdminUserController playerStatsDash = fxmlLoader.getController();
        playerStatsDash.initialize();


        childPane.getChildren().clear();
        childPane.getChildren().add(content);

        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }

    public static void loadAdminPlayerStat(AnchorPane childPane) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Misc.class.getResource("AdminPlayerStats.fxml"));
        AnchorPane content=fxmlLoader.load();
        AdminPlayerStats playerStatsDash = fxmlLoader.getController();
        playerStatsDash.initialize();


        childPane.getChildren().clear();
        childPane.getChildren().add(content);

        AnchorPane.setTopAnchor(content, 0.0);
        AnchorPane.setBottomAnchor(content, 0.0);
        AnchorPane.setLeftAnchor(content, 0.0);
        AnchorPane.setRightAnchor(content, 0.0);
    }
}
