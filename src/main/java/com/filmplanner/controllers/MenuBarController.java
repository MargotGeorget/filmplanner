package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.facades.LoginFacade;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;

import java.io.IOException;

public class MenuBarController {

    @FXML
    private MenuBar addClient;

    @FXML
    private MenuBar viewClients;

    @FXML
    private MenuBar addProject;

    @FXML
    private MenuBar viewProjects;

    private final LoginFacade  loginFacade;

    public MenuBarController() {
        this.loginFacade=LoginFacade.getInstance();
    }

    public void addClientAction() throws IOException {
        App.setRoot("views/client/clientForm");
    }

    public void viewClientsAction() throws IOException {
        App.setRoot("views/client/clientView");
    }

    public void createProjectAction() throws IOException {
        App.setRoot("views/project/projectCreation");
    }

    public void viewProjectsAction() throws IOException {
        App.setRoot("views/project/projectsView");
    }

    public void addGearAction() throws IOException {
        if(this.loginFacade.getCurrentUser().isAdmin()) {
            App.setRoot("views/gear/createGear");
        }
        else {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText("You have to be admin");
            invalidCredentials.show();
        }
    }

    public void viewGearAction() throws IOException {
        App.setRoot("views/gear/gearView");
    }
    public void addUserAction() throws IOException {
        if(this.loginFacade.getCurrentUser().isAdmin()) {
            App.setRoot("views/user/userCreationForm");
        }
        else {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText("You have to be admin");
            invalidCredentials.show();
        }
    }

    public void viewUserAction() throws IOException {
        App.setRoot("views/user/userView");
    }

    public void calendar() throws IOException {
        App.setRoot("views/calendar");
    }
}
