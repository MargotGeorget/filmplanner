package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.controllers.client.ClientInformationController;
import com.filmplanner.controllers.shoot.ShootFormController;
import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeControllers {

    @FXML
    private Button login;

    @FXML
    public void login() throws IOException {
        ProjectDAO projectDAO = PostgreDAOFactory.getInstance().getProjectDAO();
        Project project = projectDAO.findById(16L);
        //App.setRoot("views/login");
        Stage stage = new Stage();
        stage.setHeight(500);
        stage.setWidth(800);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/shootForm.fxml"));
        try {
            ShootFormController controller = new ShootFormController(project);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
