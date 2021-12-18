package com.filmplanner.controllers.gear;

import com.filmplanner.facades.GearFacade;
import com.filmplanner.models.Gear;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class GearUpdateController implements Initializable {
    @FXML
    private TextField serialNumber;
    @FXML
    private TextField model;
    @FXML
    private TextField category;
    private GearFacade gearFacade;

    private Gear gear;
    private Stage stage;

    public GearUpdateController(Gear gear, Stage stage) {
        this.gear = gear;
        this.stage = stage;
        this.gearFacade = GearFacade.getInstance();
    }

    /**
     * this fonction is execute when the validate boutton is pressed
     * It updates a gear
     */
    @FXML
    public void update() {

        String serialNumber = this.serialNumber.getText();
        String model = this.model.getText();
        String category = this.category.getText();


        if (serialNumber.equals("") || model.equals("") || category.equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Field error");
            alert.setHeaderText("Not all fields are filled");
            alert.setContentText("Please fill all the fields ");
            alert.show();
        } else {

            Gear newGear = new Gear(serialNumber, model, category);
            boolean done = gearFacade.update(this.gear.getSerialNumber(), newGear);

            if (done) {
                Alert addedGear = new Alert(Alert.AlertType.INFORMATION);
                addedGear.setContentText("Operation done successfully\nGear " + newGear.getSerialNumber() + " updated!");
                addedGear.show();
                stage.close();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.serialNumber.setText(gear.getSerialNumber());
        } catch (Exception e) {
            System.out.println("No field for Serial number");
        }
        try {
            this.model.setText(gear.getModel());
        } catch (Exception e) {
            System.out.println("No field for Model");
        }
        try {
            this.category.setText(gear.getCategory());
        } catch (Exception e) {
            System.out.println("No field for Category");
        }

    }
}
