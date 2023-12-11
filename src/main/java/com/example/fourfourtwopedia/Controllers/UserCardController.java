package com.example.fourfourtwopedia.Controllers;

import com.example.fourfourtwopedia.Interface.CompetetionListener;
import com.example.fourfourtwopedia.Interface.UserListener;
import com.example.fourfourtwopedia.Model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class UserCardController {
    @FXML
    private Label competetionName;
    @FXML
    private Label type;
    private User user;
    private UserListener userListener;
    @FXML
    void onClickAction(MouseEvent event) {
        userListener.click(user);
    }

    @FXML
    public void initialize(User user, UserListener userListener) {
        this.user=user;
        this.competetionName.setText(user.getUsername());
        this.type.setText(user.getRole().toString());
        this.userListener=userListener;
    }
}
