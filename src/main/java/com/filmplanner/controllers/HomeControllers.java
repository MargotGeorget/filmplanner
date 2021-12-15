package com.filmplanner.controllers;

import com.filmplanner.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeControllers {

    @FXML
    private Button login;

    @FXML
    public void login() throws IOException {

        App.setRoot("views/client/loginView");

    }
}
