package com.filmplanner.controllers.project;

import com.filmplanner.App;
import com.filmplanner.controllers.client.ClientInformationController;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.facades.ProjectFacade;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ProjectViewController implements Initializable {

    @FXML
    private ListView<Project> projectList;

    @FXML
    private TextField searchInput;

    private ProjectFacade projectFacade;

    public ProjectViewController() {
        this.projectFacade = ProjectFacade.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User currentUser = LoginFacade.getInstance().getCurrentUser();
        Project[] projects = this.projectFacade.findManyByManager(currentUser);

        this.projectList.setItems(FXCollections.observableList(Arrays.asList(projects)));

        this.projectList.setOnMouseClicked(event -> {

            // Retrieves the selected project
            Project project = projectList.getSelectionModel().getSelectedItem();

            // Creates new stage to show project information
            Stage stage = new Stage();
            //stage.setHeight(400);
            //stage.setWidth(610);
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/project/projectInformation.fxml"));
            try {
                ProjectInformationController controller = new ProjectInformationController(project, stage);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        });
    }
}
