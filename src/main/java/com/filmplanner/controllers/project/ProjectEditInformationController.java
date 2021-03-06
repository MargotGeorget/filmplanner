package com.filmplanner.controllers.project;

import com.filmplanner.App;
import com.filmplanner.controllers.shoot.ShootFormController;
import com.filmplanner.controllers.shoot.ShootViewController;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.facades.ProjectFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;
import com.filmplanner.models.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProjectEditInformationController implements Initializable {

    @FXML
    TextField projectName;

    @FXML
    TextField projectDescription;

    @FXML
    ComboBox<Client> client;

    @FXML
    ListView<User> usersList;

    @FXML
    ListView<User> managersList;

    @FXML
    ListView<Shoot> shootsList;


    // Attributes
    private ProjectFacade projectFacade;
    private ClientFacade clientFacade;
    private ShootFacade shootFacade;
    private Project project;
    private Stage stage;

    public ProjectEditInformationController(Project project, Stage stage) {
        this.projectFacade = ProjectFacade.getInstance();
        this.clientFacade = ClientFacade.getInstance();
        this.shootFacade = ShootFacade.getInstance();
        this.project = project;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.projectName.setText(this.project.getName());
        this.projectDescription.setText(this.project.getDescription());
        this.client.setItems(FXCollections.observableList(this.clientFacade.findAll()));
        this.client.getSelectionModel().select(this.project.getClient());

        this.usersList.setItems(FXCollections.observableList(this.getRemainingUsersFromManagersList(this.project.getManagers())));
        this.usersList.setOnMouseClicked(event -> {
            User clickedUser = this.usersList.getSelectionModel().getSelectedItem();
            if (clickedUser != null) {
                this.usersList.getItems().remove(clickedUser);
                this.managersList.getItems().add(clickedUser);
                this.project.addManager(clickedUser);
            }
        });

        this.managersList.setItems(FXCollections.observableList(this.project.getManagers()));
        this.managersList.setOnMouseClicked(event -> {
            User clickedUser = this.managersList.getSelectionModel().getSelectedItem();
            if (clickedUser != null) {
                this.usersList.getItems().add(clickedUser);
                this.managersList.getItems().remove(clickedUser);
                this.project.removeManager(clickedUser);
            }
        });


        this.initShoots();

    }

    public void initShoots(){
        this.shootsList.setItems(FXCollections.observableList(this.shootFacade.findAllShootInProject(this.project.getId())));
        this.shootsList.setOnMouseClicked(event -> {
            Shoot clickedShoot = this.shootsList.getSelectionModel().getSelectedItem();
            if (clickedShoot != null) {
                Stage stage = new Stage();
                stage.setHeight(500);
                stage.setWidth(800);
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/shootInformation.fxml"));
                try {
                    ShootViewController controller = new ShootViewController(clickedShoot, stage);
                    fxmlLoader.setController(controller);
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.showAndWait();
                    this.initShoots();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void validateAction() throws IOException {
        this.project.setName(this.projectName.getText());
        this.project.setDescription(this.projectDescription.getText());
        this.project.setClient(this.client.getSelectionModel().getSelectedItem());
        this.projectFacade.updateById(project.getId(), project);

        Alert addedClient = new Alert(Alert.AlertType.CONFIRMATION);
        addedClient.setContentText("Successfully updated project \"" + this.project.getName() + "\"!");
        addedClient.show();

        App.setRoot("views/project/projectsView"); // reloads the projectView
        this.stage.close();
    }

    /**
     * Subtracts a list of project managers to the list of all users of the system.
     *
     * @param managers the list of managers to subtract
     * @return a list of all users minus the given list of managers
     */
    private List<User> getRemainingUsersFromManagersList(List<User> managers) {

        PostgreDAOFactory factory = PostgreDAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        List<User> allUsers = userDAO.findAll();

        List<User> remainingUsers = new ArrayList<>(allUsers);

        // Removes all users of managers list to the allUsers list
        for (User user : allUsers) {
            for (User manager : managers) {
                if (user.getId().equals(manager.getId())) {
                    remainingUsers.remove(user);
                }
            }
        }

        return remainingUsers;
    }

    public void addShootAction() {
        Stage stage = new Stage();
        stage.setHeight(500);
        stage.setWidth(800);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/shootForm.fxml"));
        try {
            ShootFormController controller = new ShootFormController(this.project, stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.showAndWait();
            this.initShoots();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
