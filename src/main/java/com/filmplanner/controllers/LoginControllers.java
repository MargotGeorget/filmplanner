package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidCredentialsException;
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

        System.out.println("Email: " + email + " Password: " + password);

        try {
            this.loginFacade = new LoginFacade(); // TODO convert LoginFacade to Singleton ???
            User loggedUser = this.loginFacade.login(email, password);
            System.out.println("Logged in " + loggedUser.getName());

        } catch (InvalidCredentialsException e) {
            System.out.println("Invalid credentials");
            Alert invalidCrendentials = new Alert(Alert.AlertType.ERROR);
            invalidCrendentials.setContentText("Invalid credentials");
            invalidCrendentials.show();
        }

        try {
            App.setRoot("views/home");
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }

}
