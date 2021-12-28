package com.filmplanner.controllers.shoot;

import com.filmplanner.facades.GearWithinAShootFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Gear;
import com.filmplanner.models.Location;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ShootViewController implements Initializable {

    @FXML
    private Label name;

    @FXML
    private Label description;

    @FXML
    private Label date;

    @FXML
    private Label location;

    @FXML
    private TableView<Gear> gears;
    @FXML
    private TableColumn<Gear, Integer> colSerialNumber;
    @FXML
    private TableColumn<Gear, String> colModel;
    @FXML
    private TableColumn<Gear, String> colCategory;

    private ShootFacade shootFacade;

    private GearWithinAShootFacade gearWithinAShootFacade;

    private Shoot shoot;

    public ShootViewController(Shoot shoot) {
        this.shoot = shoot;
        this.shootFacade = ShootFacade.getInstance();
        this.gearWithinAShootFacade = GearWithinAShootFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.name.setText(shoot.getName());
        this.description.setText(shoot.getDescription());
        this.date.setText(shoot.getDate());
        Location loc = shoot.getLocation();
        this.location.setText(loc.getStreetNumber() + " " + loc.getStreet() + "\n" + loc.getCity() + " " + loc.getZipCode());

        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.gears.getItems().addAll(this.gearWithinAShootFacade.getAllGearsWithinAShoot(shoot.getIdShoot()));
    }
}
