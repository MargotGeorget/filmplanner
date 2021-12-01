package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.views.Router;
import com.filmplanner.views.Views;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllers {

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button validate;

    // location and resources will be automatically injected by the FXML loader
    @FXML
    private URL location;

    @FXML
    private ResourceBundle resources;

    private LoginFacade loginFacade;

    private Router router;

    public LoginControllers() {}

    @FXML
    private boolean validateCredentials() {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        System.out.println("Email: " + email + " Password: " + password);
        //loginFacade.login(email, password);

        try{
            App.setRoot("views/home");
            //router.navigateTo(Views.ACCUEIL);
        }catch (IOException e){
            System.out.println(e);
        }

        return true;
    }

}
