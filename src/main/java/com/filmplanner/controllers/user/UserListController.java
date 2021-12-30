package com.filmplanner.controllers.user;

import com.filmplanner.App;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.facades.UserFacade;
import com.filmplanner.models.Gear;
import com.filmplanner.models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserListController implements Initializable {


    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> NameColumn;
    @FXML
    private TableColumn<User, String> EmailColumn;
    @FXML
    private TableColumn<User, Long> IDColumn;
    @FXML
    private TableColumn<User, String> PhoneColumn;
    @FXML
    private TableColumn<User, Boolean> AdminColumn;


    private UserFacade userFacade;

    private final LoginFacade loginFacade;

    /**
     * Gets the facade instance and assigns it to a variable
     */
    public UserListController() {
        this.userFacade = UserFacade.getInstance();
        this.loginFacade = LoginFacade.getInstance();
    }
    private ObservableList<User> usersData = FXCollections.observableArrayList();


    /**
     * @param url
     * @param resourceBundle
     * Initialization of the table, put the values inside the table
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         usersData = FXCollections.observableArrayList(userFacade.findAll());
        //Add values to the table
        this.userTable.setItems(usersData);
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        EmailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        PhoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumberProperty());
        IDColumn.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asObject());
        AdminColumn.setCellValueFactory(cellData -> cellData.getValue().getIsAdminProperty().asObject());


    }

    public void CreateUser() throws IOException {
        if(this.loginFacade.getCurrentUser().isAdmin()) {
            App.setRoot("views/user/userCreationForm");
        }
        else {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText("You have to be admin");
            invalidCredentials.show();
        }

    }

    /**
     * Deletes a user selected by the user when the user clicks on delete button
     * A confirmation prompt is shown
     */
    @FXML

    private void DeleteUser() {
        if(this.loginFacade.getCurrentUser().isAdmin()) {
            int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            if (selectedIndex >= 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete User");
                alert.setHeaderText("Are you sure want to delete this user?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    userFacade.delete(selectedUser.getId());
                    userTable.getItems().remove(selectedIndex);
                }
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No User Selected");
                alert.setContentText("Please select a user in the table.");
                alert.show();
            }
        }
        else {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText("You have to be admin");
            invalidCredentials.show();
        }

    }

    /**
     * When the user chooses a user in the list and clicks on edit button, open a new edit window where the user can edit
     */
    @FXML

    private void EditUser() {
        if(this.loginFacade.getCurrentUser().isAdmin()) {
            int selectedIndex = userTable.getSelectionModel().getSelectedIndex();
            User selectedUser = userTable.getSelectionModel().getSelectedItem();
            if (selectedIndex >= 0) {
                //Create new stage to edit client

                Stage stage = new Stage();
                stage.setHeight(400);
                stage.setWidth(610);
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/user/userUpdateForm.fxml"));
                try {
                    UserUpdateFormController controller = new UserUpdateFormController(selectedUser, stage);
                    fxmlLoader.setController(controller);
                    Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.showAndWait();
                this.usersData = FXCollections.observableArrayList(userFacade.findAll());
                //Add values to the table
                this.userTable.setItems(usersData);
                NameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
                EmailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
                PhoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumberProperty());
                IDColumn.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asObject());

            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Selection");
                alert.setHeaderText("No User Selected");
                alert.setContentText("Please select a user in the table.");
                alert.show();
            }
        }
        else {
            Alert invalidCredentials = new Alert(Alert.AlertType.ERROR);
            invalidCredentials.setContentText("You have to be admin");
            invalidCredentials.show();
        }

    }


}
