package com.filmplanner.controllers.shoot;

import com.filmplanner.App;
import com.filmplanner.controllers.client.ClientFormUpdateController;
import com.filmplanner.controllers.client.ClientInformationController;
import com.filmplanner.facades.GearWithinAShootFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.Gear;
import com.filmplanner.models.Location;
import com.filmplanner.models.Shoot;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

    private Stage stage;

    public ShootViewController(Shoot shoot, Stage stage) {
        //TODO: Add verif shoot empty?
        this.shoot = shoot;
        this.shootFacade = ShootFacade.getInstance();
        this.gearWithinAShootFacade = GearWithinAShootFacade.getInstance();
        this.stage = stage;
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

    public void reload(){
        this.gears.getItems().clear();
        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.gears.getItems().addAll(this.gearWithinAShootFacade.getAllGearsWithinAShoot(shoot.getIdShoot()));
    }

    /**
     * Update the current page to allow the user to modify the information of the selected shoot
     *
     * @throws IOException
     */
    public void editShootAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/shootFormUpdate.fxml"));
        try {
            ShootFormUpdateController controller = new ShootFormUpdateController(this.shoot, this.stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
            stage.setScene(scene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the selected client and return to the client view
     *
     * @throws IOException
     */
    public void deleteShootAction() throws IOException {
        this.shootFacade.delete(shoot.getIdShoot());
        //TODO: verif
        //Display alert
        Alert delatedShoot = new Alert(Alert.AlertType.CONFIRMATION);
        delatedShoot.setContentText("Operation done successfully\nShoot " + this.shoot.getName() + " deleted!");
        delatedShoot.show();

        //Reload listView and close update stage
        App.setRoot("views/client/clientView");
        this.stage.close();
    }

    public void addGearToAShootAction() {
        Stage stage = new Stage();
        stage.setHeight(300);
        stage.setWidth(486);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/addGearToAShoot.fxml"));
        try {
            AddGearToAShootController controller = new AddGearToAShootController(this.shoot, stage);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        System.out.println("yo");
        this.reload();
    }
}
