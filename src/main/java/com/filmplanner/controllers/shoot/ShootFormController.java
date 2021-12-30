package com.filmplanner.controllers.shoot;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.facades.LocationFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Location;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ShootFormController {

    @FXML
    public TextField nameInput;

    @FXML
    public TextField dateInput;

    @FXML
    public TextField numberInput;

    @FXML
    public TextField streetInput;

    @FXML
    public TextField cityInput;

    @FXML
    public TextField zipCodeInput;

    @FXML
    public TextArea descriptionInput;

    private ShootFacade shootFacade;

    private Project project;

    public ShootFormController(Project project) {
        this.project = project;
        this.shootFacade = ShootFacade.getInstance();
    }

    /**
     * Retrieves the information entered by the user and creates the associated shoot
     * If the creation is completed, display an alert and return to the home page of the project view
     * If the information given by the user is wrong, display an alert and stays on this page
     * @throws IOException
     */
    public void createShoot() throws IOException {

        //Recovery of data entered
        String name = nameInput.getText().trim();
        String date = dateInput.getText().trim();
        int number = Integer.parseInt(numberInput.getText());
        String street = streetInput.getText().trim();
        String city = cityInput.getText().trim();
        String zipCode = zipCodeInput.getText().trim();
        String description = descriptionInput.getText();

        try {
            //Create localisation
            Location location = new Location(number, street, city, zipCode);

            //Create shoot variable
            Shoot newShoot = new Shoot(name, description, date, location, project);

            //Sends shoot to create to facade
            long idShoot = shootFacade.createShoot(newShoot);
            newShoot.setIdShoot(idShoot);

            if (idShoot != -1) {
                Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                message.setContentText("Operation done successfully\nShoot " + newShoot.getName() + " created!");
                message.show();
                App.setRoot("views/client/clientView");
            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Error in client creation\nShoot " + newShoot.getName() + "not created!\nError with database");
                message.show();
            }
        } catch (InvalidInputException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Creation cancelled\nError in client input: " + e.getMessage() + "!");
            message.show();
        }

    }

    /**
     * Return to the home page of the client view
     *
     * @throws IOException
     */
    public void cancelCreation() throws IOException {
        App.setRoot("views/client/clientView");
    }

}
