package com.filmplanner.controllers.shoot;

import com.filmplanner.App;
import com.filmplanner.facades.GearWithinAShootFacade;
import com.filmplanner.facades.ShootFacade;
import com.filmplanner.models.*;
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
import java.lang.reflect.Member;
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
    @FXML
    private TableView<UserRole> memberTable;
    @FXML
    private TableColumn<UserRole, String> colName;
    @FXML
    private TableColumn<UserRole, String> colAdress;
    @FXML
    private TableColumn<UserRole, String> colRole;

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
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

        this.gears.getItems().addAll(this.gearWithinAShootFacade.getAllGearsWithinAShoot(shoot.getIdShoot()));
        this.memberTable.getItems().addAll(this.shootFacade.allUserInAShoot(shoot));
    }

    public void reload(){
        this.gears.getItems().clear();
        this.memberTable.getItems().clear();

        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("email"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        this.memberTable.getItems().addAll(this.shootFacade.allUserInAShoot(shoot));
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

    public void deleteGearWithinAShootAction() {
        Gear gear = gears.getSelectionModel().getSelectedItem();
        if(gear==null){
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Error in delete gear to a shoot\nNo gear selected");
            message.show();
        }else{
            GearWithinAShoot gearWithinAShoot = this.gearWithinAShootFacade.getOne(this.shoot.getIdShoot(), gear.getSerialNumber());
            this.gearWithinAShootFacade.delete(gearWithinAShoot.getId());
            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setContentText("Operation done successfully\nGear deleted to this shoot");
            message.show();
            this.reload();
        }
    }

    public void addMemberToAShootAction() {
        Stage stage = new Stage();
        stage.setHeight(300);
        stage.setWidth(486);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/shoot/addMemberToAShoot.fxml"));
        try {
            AddMemberToAShootController controller = new AddMemberToAShootController(stage, this.shoot);
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(), stage.getHeight());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.showAndWait();
        this.reload();
    }

    public void deleteMemberWithinAShootAction() {
        UserRole member = memberTable.getSelectionModel().getSelectedItem();
        if (member == null) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Error in delete member to a shoot\nNo member selected");
            message.show();
        } else {
            this.shootFacade.deleteUserInAShoot(this.shoot, member.getUser());
            Alert message = new Alert(Alert.AlertType.CONFIRMATION);
            message.setContentText("Operation done successfully\nMember deleted to this shoot");
            message.show();
            this.reload();
        }
    }
}
