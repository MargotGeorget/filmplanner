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

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    private LoginFacade loginFacade;

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
            LoginFacade.getInstance().login(email, password);
            App.setRoot("views/calendar");
        } catch (InvalidCredentialsException | UserNotFoundException e) {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText(e.getMessage());
            invalidCredentials.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
