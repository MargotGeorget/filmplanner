package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.controllers.client.ClientInformationController;
import com.filmplanner.controllers.shoot.ShootFormController;
import com.filmplanner.controllers.shoot.ShootFormUpdateController;
import com.filmplanner.controllers.shoot.ShootViewController;
import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeControllers {

    @FXML
    private Button login;

    @FXML
    public void login() throws IOException {
        App.setRoot("views/login");
    }
}
