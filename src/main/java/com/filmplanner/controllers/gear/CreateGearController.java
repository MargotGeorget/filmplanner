package com.filmplanner.controllers.gear;

import com.filmplanner.App;
import com.filmplanner.facades.GearFacade;
import com.filmplanner.models.Gear;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateGearController implements Initializable {
    @FXML
    private TextField serialNumberfield;
    @FXML
    private TextField modelfield;
    @FXML
    private TextField categoryfield;

    private GearFacade gearFacade;

    public CreateGearController() {
        this.gearFacade = GearFacade.getInstance();
    }

    /**
     * Execute when the creation button is pressed to create a gear
     * It creates
     * @throws IOException
     */
    @FXML
    public void validateCreation() throws IOException {
        Gear gear = new Gear(serialNumberfield.getText(), modelfield.getText(), categoryfield.getText());
        this.gearFacade.create(gear);
        App.setRoot("views/gear/gearView");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
