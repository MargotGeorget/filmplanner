package com.filmplanner.views;

import com.filmplanner.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Router {

    private final Stage stage;

    /**
     * @param primaryStage the stage to change Scene of when navigating
     */

    public Router(Stage primaryStage)
    {
        this.stage = primaryStage;
    }

    public void navigateTo(String viewPath) throws IOException {
        this.stage.setScene(new Scene(new FXMLLoader(App.class.getResource(viewPath + ".fxml")).load(), stage.getWidth(),stage.getHeight()));
        this.stage.centerOnScreen();
    }
}
