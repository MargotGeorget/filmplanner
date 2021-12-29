package com.filmplanner.controllers.shoot;

import com.filmplanner.App;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.facades.GearFacade;
import com.filmplanner.facades.GearWithinAShootFacade;
import com.filmplanner.facades.LocationFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Gear;
import com.filmplanner.models.GearWithinAShoot;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

    private GearWithinAShootFacade gearWithinAShootFacade;

    private Shoot shoot;

    private Stage stage;

    public AddGearToAShootController(Shoot shoot, Stage stage){
        this.shoot = shoot;
        this.shootFacade = ShootFacade.getInstance();
        this.gearFacade = GearFacade.getInstance();
        this.gearWithinAShootFacade = GearWithinAShootFacade.getInstance();
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.gears.getItems().addAll(this.gearFacade.getAllGear());
    }

    public void addGearToAShoot() throws IOException {
        Gear gear = gears.getSelectionModel().getSelectedItem();
        if(gear==null){
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Error in added gear to a shoot\nNo gear selected");
            message.show();
        }else{
            GearWithinAShoot gearWithinAShoot = new GearWithinAShoot(this.shoot.getIdShoot(), gear.getSerialNumber());
            boolean isCreated = this.gearWithinAShootFacade.create(gearWithinAShoot);
            if (isCreated) {
                Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                message.setContentText("Operation done successfully\n Gear " + gear.getModel() + " added to the shoot!");
                message.show();
                this.stage.close();
            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Error in added gear to a shoot\nGear " + gear.getModel() + "not added to the shoot!\nError with database");
                message.show();
            }
        }
    }
}
