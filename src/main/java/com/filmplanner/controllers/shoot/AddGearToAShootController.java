package com.filmplanner.controllers.shoot;

import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.facades.GearFacade;
import com.filmplanner.facades.GearWithinAShootFacade;
import com.filmplanner.facades.LocationFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Gear;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class AddGearToAShootController implements Initializable {
    @FXML
    private TableView<Gear> gears;
    @FXML
    private TableColumn<Gear, Integer> colSerialNumber;
    @FXML
    private TableColumn<Gear, String> colModel;
    @FXML
    private TableColumn<Gear, String> colCategory;

    private ShootFacade shootFacade;

    private GearFacade gearFacade;

    private Shoot shoot;

    public AddGearToAShootController(Shoot shoot){
        this.shoot = shoot;
        this.shootFacade = ShootFacade.getInstance();
        this.gearFacade = GearFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.gears.getItems().addAll(this.gearFacade.getAllGear());
    }
}
