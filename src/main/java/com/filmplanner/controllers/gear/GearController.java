package com.filmplanner.controllers.gear;

import com.filmplanner.App;

import com.filmplanner.facades.GearFacade;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.models.Gear;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class GearController implements Initializable {


    @FXML
    private TableView<Gear> gearList;
    @FXML
    private TableColumn<Gear, Integer> colSerialNumber;
    @FXML
    private TableColumn<Gear,String> colModel;
    @FXML
    private TableColumn<Gear,String > colCategory;


    private GearFacade gearFacade;

    public GearController() {
        this.gearFacade=GearFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colSerialNumber.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.gearList.getItems().addAll(this.gearFacade.getAllGear());
    }

    @FXML
    public void add() throws IOException {
        App.setRoot("views/gear/createGear");

    }

    /**
     *
     */
    @FXML
    public void edit(){
        int selectedIndex = gearList.getSelectionModel().getSelectedIndex();
        Gear selectedGear = gearList.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {

            Stage stage = new Stage();
            stage.setHeight(400);
            stage.setWidth(610);
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/gear/updateGear.fxml"));
            try {
                GearUpdateController controller = new GearUpdateController(selectedGear, stage);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.showAndWait();
            this.gearList.getItems().clear();
            this.gearList.getItems().addAll(gearFacade.getAllGear());

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Gear Selected");
            alert.setContentText("Please select a gear in the table.");
            alert.show();
        }
    }


    @FXML
    public void delete(){
        int selectedIndex = gearList.getSelectionModel().getSelectedIndex();
        Gear gearSelected = gearList.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Gear");
            alert.setHeaderText("Are you sure want to delete this gear?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                gearFacade.delete(gearSelected.getSerialNumber());
                gearList.getItems().remove(selectedIndex);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Gear Selected");
            alert.setContentText("Please select a gear in the table.");
            alert.show();
        }

    }
}
