package com.filmplanner.controllers.project;

import com.filmplanner.App;
import com.filmplanner.facades.ProjectFacade;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;
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

    @FXML Label projectClient;

    @FXML
    private ListView<User> usersList;

    @FXML
    private ListView<Shoot> shootsList;


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
        this.projectClient.setText(this.project.getClient().toString());
        this.usersList.setItems(FXCollections.observableList(this.project.getManagers()));
        this.shootsList.setItems(FXCollections.observableList(this.project.getShoots()));
    }


    /*
    Buttons actions
     */

    public void editProjectAction() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/project/projectEditInformation.fxml"));
        try {
            ProjectEditInformationController controller = new ProjectEditInformationController(project, this.stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(), stage.getHeight());
            this.stage.setScene(scene);
        } catch (IOException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("An error occurred while entering edit mode: " + e.getMessage());
            message.show();
            e.printStackTrace();
        }
    }

    public void deleteProjectAction() throws IOException {
        boolean deleted = this.projectFacade.deleteById(this.project.getId());

        if (deleted) {
            Alert deletedProject = new Alert(Alert.AlertType.CONFIRMATION);
            deletedProject.setContentText("Project \"" + this.project.getName() + "\" successfully deleted!");
            deletedProject.show();

            App.setRoot("views/project/projectsView"); // reloads the projectView
            this.stage.close();
        } else {
            Alert projectNotDeleted = new Alert(Alert.AlertType.ERROR);
            projectNotDeleted.setContentText("Project \"" + this.project.getName() + "\" could not be deleted, make sure to remove all shoots.");
            projectNotDeleted.show();
        }
    }

    /* code to open view shoot

        Stage stage = new Stage();
        stage.setHeight(500);
        stage.setWidth(800);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/shootInformation.fxml"));
        try {
            //TODO: donne rne paramètre de shoot selectionné
            ShootViewController controller = new ShootViewController(shoot, stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
     */
}
