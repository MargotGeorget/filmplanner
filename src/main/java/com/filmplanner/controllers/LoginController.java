package com.filmplanner.controllers;

import com.filmplanner.exceptions.InvalidCredentialsException;
import com.filmplanner.exceptions.UserNotFoundException;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    public LoginController() {
    }

    /**
     * Called when the "validate" button is pressed.
     */
    @FXML
    private void login() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        try {
            User loggedUser = LoginFacade.getInstance().login(email, password);
            // TODO create a method to display alerts
            Alert invalidCredentials = new Alert(Alert.AlertType.INFORMATION);
            invalidCredentials.setContentText("Logged in!\nWelcome " + loggedUser.getName());
            invalidCredentials.show();
        } catch (InvalidCredentialsException | UserNotFoundException e) {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText(e.getMessage());
            invalidCredentials.show();
        }
    }
}
