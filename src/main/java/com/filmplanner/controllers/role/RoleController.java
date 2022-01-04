package com.filmplanner.controllers.role;

import com.filmplanner.facades.RoleFacade;
import com.filmplanner.models.Role;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoleController {
    private Stage stage;
    @FXML
    private TextField roleName;
    private RoleFacade roleFacade;

    public RoleController(Stage stage) {
        this.stage = stage;
        this.roleFacade = RoleFacade.getInstance();
    }
    @FXML
    void add() {
        String text = this.roleName.getText();
        if (text == null) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("No entry\n");
            message.show();

        } else {
            boolean success = this.roleFacade.createRole(new Role(text));
            if (success) {
                Alert message = new Alert(Alert.AlertType.INFORMATION);
                message.setContentText("Creation done\n");
                message.show();
                stage.close();
            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Error role already exist\n");
                message.show();

            }
        }

    }
}
