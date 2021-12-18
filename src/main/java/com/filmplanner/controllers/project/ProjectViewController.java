package com.filmplanner.controllers.project;

import com.filmplanner.facades.ProjectFacade;
import com.filmplanner.models.Project;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ProjectViewController implements Initializable {

    @FXML
    private ListView<Project> projectList;

    private ProjectFacade projectFacade;

    public ProjectViewController() {
        this.projectFacade = ProjectFacade.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Project[] projects = this.projectFacade.findAll();

        this.projectList.setItems(FXCollections.observableList(Arrays.asList(projects)));

    }
}
