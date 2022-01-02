package com.filmplanner.controllers.shoot;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.facades.LocationFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Location;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShootFormUpdateController implements Initializable {
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

    private LocationFacade locationFacade;

    private Shoot shoot;

    private Stage stage;


    public ShootFormUpdateController(Shoot shoot, Stage stage) {
        this.shoot = shoot;
        this.shootFacade = ShootFacade.getInstance();
        this.locationFacade = LocationFacade.getInstance();
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nameInput.setText(this.shoot.getName());
        this.descriptionInput.setText(this.shoot.getDescription());
        this.dateInput.setText(this.shoot.getDate());
        Location loc = this.shoot.getLocation();
        this.numberInput.setText(String.valueOf(loc.getStreetNumber()));
        this.streetInput.setText(loc.getStreet());
        this.cityInput.setText(loc.getCity());
        this.zipCodeInput.setText(loc.getZipCode());
    }

    /**
     * Retrieves the information entered by the user and creates the associated shoot
     * If the creation is completed, display an alert and return to the home page of the project view
     * If the information given by the user is wrong, display an alert and stays on this page
     * @throws IOException
     */
    public void updateShoot() throws IOException {

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
            Location currentLocation = shoot.getLocation();
            Location newLocation = new Location(number, street, city, zipCode);
            if(!currentLocation.eguals(newLocation)) {
                //il faut créer une nouvelle location
                newLocation.setId(currentLocation.getId());
                this.locationFacade.update(newLocation);
                currentLocation = newLocation;
            }
            //Create new shoot variable
            //Le project ne peut pas changer
            Shoot newShoot = new Shoot(shoot.getIdShoot(), name, description, date, currentLocation, shoot.getProjectId());

            //Sends shoot to create to facade
            boolean isUpdated = shootFacade.updateShoot(newShoot);

            if (isUpdated) {
                Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                message.setContentText("Operation done successfully\nShoot " + newShoot.getName() + " updated!");
                message.show();
                App.setRoot("views/client/clientView");
            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Error in shoot update\nShoot " + newShoot.getName() + " not updated!\nError with database");
                message.show();
            }
        } catch (InvalidInputException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Creation cancelled\nError in shoot input: " + e.getMessage() + "!");
            message.show();
        }

    }

    /**
     * Return to the home page of the client view
     *
     * @throws IOException
     */
    public void cancelUpdate() throws IOException {
        App.setRoot("views/client/clientView");
    }


}
