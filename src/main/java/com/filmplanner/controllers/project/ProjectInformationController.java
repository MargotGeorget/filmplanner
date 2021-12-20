package com.filmplanner.controllers.project;

import com.filmplanner.App;
import com.filmplanner.facades.ProjectFacade;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectInformationController implements Initializable {

    // FXML elements

    @FXML
    private Label projectName;

    @FXML
    private Label projectDescription;

    @FXML
    private ListView<User> usersList;


    // Attributes
    private ProjectFacade projectFacade;
    private Stage stage;
    private Project project;

    public ProjectInformationController(Project project, Stage stage) {
        this.project = project;
        this.stage = stage;
        this.projectFacade = ProjectFacade.getInstance();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.projectName.setText(this.project.getName());
        this.projectDescription.setText(this.project.getDescription());
        this.usersList.setItems(FXCollections.observableList(this.project.getManagers()));
    }


    /*
    Buttons actions
     */

    public void editProjectAction() throws IOException {
        // Creates new stage to show project information
        Stage stage = new Stage();
        stage.setHeight(280);
        stage.setWidth(610);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/project/projectInformationUpdate.fxml"));
        try {
            ProjectInformationUpdateController controller = new ProjectInformationUpdateController(project, stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public void deleteProjectAction() throws IOException {
        this.projectFacade.deleteById(this.project.getId());

        Alert deletedProject = new Alert(Alert.AlertType.CONFIRMATION);
        deletedProject.setContentText("Project " + this.project.getName() + " successfully deleted!");
        deletedProject.show();

        App.setRoot("views/project/projectsView");
        this.stage.close();
    }
}
