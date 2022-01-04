package com.filmplanner.controllers.shoot;


import com.filmplanner.App;
import com.filmplanner.controllers.role.RoleController;
import com.filmplanner.controllers.user.UserUpdateFormController;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.facades.RoleFacade;
import com.filmplanner.facades.ShootFacade;

import com.filmplanner.models.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddMemberToAShootController implements Initializable {
    @FXML
    private TableView<User> memberTable;
    @FXML
    private TableView<Role> roleTable;
    @FXML
    private TableColumn<User, String> NameColumn;
    @FXML
    private TableColumn<Role, String> RoleColumn;


    private ShootFacade shootFacade;

    private RoleFacade roleFacade;

    private Shoot currentShoot;

    private Stage stage;

    AddMemberToAShootController(Stage stage, Shoot shoot) {
        this.stage = stage;
        this.currentShoot = shoot;
        this.shootFacade = ShootFacade.getInstance();
        this.roleFacade = RoleFacade.getInstance();
    }

    @FXML
    void add() {
        User user = this.memberTable.getSelectionModel().getSelectedItem();
        Role role = this.roleTable.getSelectionModel().getSelectedItem();
        if (user == null || role == null) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Error in added member to a shoot\nNo user or role selected");
            message.show();
        } else {
            try {

                boolean isCreated = this.shootFacade.addUserToAShoot(this.currentShoot, user, role);
                if (isCreated) {
                    Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                    message.setContentText("Operation done successfully\n Member " + user.getName() + " added to the shoot!");
                    message.show();
                    this.stage.close();
                } else {
                    Alert message = new Alert(Alert.AlertType.ERROR);
                    message.setContentText("Error in added member to a shoot\nMember " + user.getName() + "not added to the shoot!\nError with database");
                    message.show();
                }
            } catch (Exception e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Error in added member to a shoot\n" + e.getMessage());
                message.show();
            }
        }

    }

    @FXML
    void cancel() throws IOException {
        this.stage.close();

    }

    @FXML
    void addRole() {
        Stage stageAddRole = new Stage();
        stageAddRole.setHeight(400);
        stageAddRole.setWidth(610);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/role/roleCreation.fxml"));
        try {
            RoleController controller = new RoleController(stageAddRole);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), stageAddRole.getWidth(), stageAddRole.getHeight());
            stageAddRole.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stageAddRole.showAndWait();
        this.roleTable.setItems(FXCollections.observableArrayList(this.roleFacade.findAllRole()));
    }

    @FXML
    void deleteRole() {

        int selectedIndex = roleTable.getSelectionModel().getSelectedIndex();
        Role selectedRole = roleTable.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Role");
            alert.setHeaderText("Are you sure want to delete this role?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                roleFacade.deleteRole(selectedRole);
                roleTable.getItems().remove(selectedIndex);
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Role Selected");
            alert.setContentText("Please select a role in the table.");
            alert.show();
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        RoleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.roleTable.getItems().addAll(this.roleFacade.findAllRole());
        System.out.println(this.currentShoot.getDate());
        System.out.println(this.shootFacade.allUserAvailableForDate(this.currentShoot.getDate()));
        this.memberTable.getItems().addAll(this.shootFacade.allUserAvailableForDate(this.currentShoot.getDate()));

    }
}
