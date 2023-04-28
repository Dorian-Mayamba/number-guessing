package com.numbers.numbers;

import com.numbers.game.NumberGuessingGame;
import com.numbers.users.User;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TitleController {
    @FXML
    private Label welcomeText;

    @FXML
    private TextField userNameField;

    @FXML
    protected void onPlayButtonClick() throws IOException {
        String userName = userNameField.getText();
        if(userName.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty user name field");
            alert.setHeaderText("an username is required");
            alert.setContentText("an username is required");
            PauseTransition closeDelay = new PauseTransition(Duration.millis(3000));
            closeDelay.setOnFinished(e->alert.close());
            alert.setOnShown(e->closeDelay.playFromStart());
            alert.show();
            return;
        }

        Stage stage = (Stage) welcomeText.getScene().getWindow();
        stage.close();
        User user = new User(userName);
        NumberGuessingGame game = new NumberGuessingGame(user);
        game.startGame(stage);
    }
}