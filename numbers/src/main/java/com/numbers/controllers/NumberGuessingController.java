package com.numbers.controllers;

import com.numbers.exceptions.EmptyNumberFieldException;
import com.numbers.exceptions.NoRemainingAttemptsException;
import com.numbers.users.User;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class NumberGuessingController{
    @FXML
    TextField numberField;

    @FXML
    Label playerName;

    @FXML
    Label playerScore;

    @FXML

    Label remainingAttemptsLbl;

    private final int MAX_NUMBER = 1000;

    private int attempts = 0;

    private int remainingAttempts = 10;
    private String message;

    private int numberToGuess;

    private final int MAX_SCORE = 10;

    private int currentScore = 10;
    private User user;

    @FXML
    public void onSubmitClick() {
        try{
            checkEmpty(numberField.getText());
            int choice = Integer.parseInt(numberField.getText());
            attempts++;
            if (numberFound(choice,numberToGuess)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                currentScore = calculatePlayerScore(attempts);
                user.setScore(currentScore);
                alert.setTitle("Number is found");
                alert.setContentText("congrats you found the mystery number in "+ attempts + " attemps");
                PauseTransition closeDelay = new PauseTransition(Duration.seconds(3));
                closeDelay.setOnFinished(e->alert.close());
                alert.setOnShown(e->closeDelay.playFromStart());
                alert.showAndWait();
                Stage stage = (Stage) numberField.getScene().getWindow();
                stage.close();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("failure");
                if(choice < numberToGuess){
                    if (Math.abs(choice-numberToGuess) <= 10){
                        setMessage("you are getting closer !!");
                    }else{
                        setMessage("too low!!");
                    }
                    alert.setContentText(message);
                }else{
                    if (choice - numberToGuess <= 10){
                        setMessage("you are getting closer !!");
                    }else{
                        setMessage("too high!!");
                    }
                    alert.setContentText(message);
                }
                PauseTransition closeDelay = new PauseTransition(Duration.seconds(3));
                closeDelay.setOnFinished(e->alert.close());
                alert.setOnShown(e->closeDelay.playFromStart());
                alert.showAndWait();
                remainingAttempts--;
                remainingAttemptsLbl.setText(Integer.toString(remainingAttempts));
                checkRemainingAttempts(remainingAttempts);
            }

        }catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty number error");
            PauseTransition closeDelay = new PauseTransition(Duration.millis(3000));
            closeDelay.setOnFinished(e->alert.close());
            alert.setOnShown(e->closeDelay.playFromStart());
            alert.setContentText("an error as occured "+ ex.getMessage());
            alert.showAndWait();
        }catch (EmptyNumberFieldException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Empty number error");
            PauseTransition closeDelay = new PauseTransition(Duration.millis(3000));
            closeDelay.setOnFinished(e->alert.close());
            alert.setOnShown(e->closeDelay.playFromStart());
            alert.setContentText("an error as occured "+ ex.getMessage());
            alert.showAndWait();
        }catch (NoRemainingAttemptsException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No remaining try");
            PauseTransition closeDelay = new PauseTransition(Duration.millis(3000));
            closeDelay.setOnFinished(e->alert.close());
            alert.setOnShown(e->closeDelay.playFromStart());
            alert.setContentText("an error as occured "+ ex.getMessage());
            alert.showAndWait();
            Stage stage = (Stage) playerScore.getScene().getWindow();
            stage.close();
        }
    }

    private boolean numberFound(int numberGuessed, int numberToGuess){
        return numberGuessed == numberToGuess;
    }

    public void setUser(User user){
        this.user = user;
        displayPlayerName(user.getUserName());
    }
    private void displayPlayerName(String name){
        playerName.setText(name);
    }

    public void initNumberToGuess(){
        Random rnd = new Random();
        this.numberToGuess = rnd.nextInt(MAX_NUMBER) + 1;
    }

    public void initRemainingAttempts(){
        remainingAttemptsLbl.setText(Integer.toString(this.remainingAttempts));
    }

    private void checkRemainingAttempts(int remainingAttempts) throws NoRemainingAttemptsException{
        if (remainingAttempts == 0){
            throw new NoRemainingAttemptsException("No remaining attempts, GAME OVER!!!");
        }
    }

    private void checkEmpty(String numberField) throws EmptyNumberFieldException {
        if(numberField.isEmpty()){
            throw new EmptyNumberFieldException("please enter a number");
        }
    }

    public void initPlayerScore(int score){
        playerScore.setText(Integer.toString(score));
    }

    public int calculatePlayerScore(int numberOfAttemps){
        if(numberOfAttemps == 1){
            return MAX_SCORE;
        } else if (numberOfAttemps >=2 && numberOfAttemps < MAX_SCORE) {
            return MAX_SCORE - numberOfAttemps;
        }
        return 0;
    }

    private void setMessage(String message){
        this.message = message;
    }
}
