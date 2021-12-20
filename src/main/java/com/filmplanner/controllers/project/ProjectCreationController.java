package com.filmplanner.controllers.project;

import com.filmplanner.App;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.facades.ProjectFacade;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class ProjectCreationController implements Initializable {

    @FXML
    TextField projectName;

    @FXML
    TextField projectDescription;

    @FXML
    ComboBox<Client> client;

    private ProjectFacade projectFacade;
    private LoginFacade loginFacade;
    private ClientFacade clientFacade;

    public ProjectCreationController() {
        this.projectFacade = ProjectFacade.getInstance();
        this.loginFacade = LoginFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Client> clients = this.clientFacade.findAll();
        this.client.setItems(FXCollections.observableList(clients));
        this.client.getSelectionModel().select(0);
    }

    public void validateAction() throws IOException {
        User loggedUser = loginFacade.getCurrentUser();
        Map<Long, User> users = new HashMap<>();
        users.put(loggedUser.getId(), loggedUser);

        Project createdProject = new Project(this.projectName.getText(), this.projectDescription.getText(), users);
        this.projectFacade.createProject(createdProject);

        Alert message = new Alert(Alert.AlertType.INFORMATION);
        message.setContentText("Project \"" + createdProject.getName() + "\" successfully created!");
        message.show();

        App.setRoot("views/project/projectsView");
    }

    public void cancelAction() throws IOException {
        App.setRoot("views/project/projectsView");
    }
}
