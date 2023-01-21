package com.numbers.game;

import com.numbers.controllers.NumberGuessingController;
import com.numbers.users.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NumberGuessingGame {

    private User user;

    public NumberGuessingGame(User user){
        this.user = user;
    }

    public void startGame(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(NumberGuessingGame.class.getResource("/com.numbers.game/game-view.fxml"));
        Scene scene = new Scene(loader.load());
        NumberGuessingController controller = loader.getController();
        controller.setUser(user);
        controller.initPlayerScore(user.getScore());
        controller.initNumberToGuess();
        stage.setScene(scene);
        stage.setTitle("Number guessing game");
        stage.show();

    }
}
