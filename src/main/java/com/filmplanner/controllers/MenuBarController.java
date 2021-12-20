package com.filmplanner.controllers;

import com.filmplanner.App;
import javafx.fxml.FXML;
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

    public MenuBarController() {
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
}
