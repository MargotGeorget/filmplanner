package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidCredentialsException;
import com.filmplanner.exceptions.UserNotFoundException;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginControllers {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    private LoginFacade loginFacade;

    public LoginControllers() {}

    /**
     * Called when the "validate" button is pressed.
     * @return ???
     */
    // TODO specify return
    @FXML
    private boolean validateCredentials() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        try {
            this.loginFacade = new LoginFacade(); // TODO convert LoginFacade to Singleton ???
            User loggedUser = this.loginFacade.login(email, password);
            Alert invalidCredentials = new Alert(Alert.AlertType.INFORMATION);
            invalidCredentials.setContentText("Logged in!\nWelcome " + loggedUser.getName());
            invalidCredentials.show();
        } catch (InvalidCredentialsException e) {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText(e.getMessage());
            invalidCredentials.show();
        } catch (UserNotFoundException e) {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText(e.getMessage());
            invalidCredentials.show();
        }

        /*
        try {
            App.setRoot("views/home");
        } catch (IOException e) {
            System.out.println(e);
        }

         */

        return true;
    }

}
