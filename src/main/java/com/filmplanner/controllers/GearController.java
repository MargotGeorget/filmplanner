package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidCredentialsException;
import com.filmplanner.exceptions.UserNotFoundException;
import com.filmplanner.facades.GearFacade;
import com.filmplanner.facades.LoginFacade;
import com.filmplanner.models.Gear;
import com.filmplanner.models.User;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GearController implements Initializable {
    @FXML
    private TextField searchInput;

    @FXML
    private ListView<String> gearList;

    private GearFacade gearFacade;

    public GearController() {
    }

    /**
     * Called when the "validate" button is pressed.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Récupérer les clients à partir de la base de données avec Facade
        this.gearFacade=GearFacade.getInstance();
        ArrayList<Gear>  gearListe = this.gearFacade.getAllGear();

        ArrayList<String> gear = new ArrayList<>();
        for (int i = 0; i < gearListe.size(); i++) {
            gear.add(gearListe.get(i).getSerialNumber()+" "+ gearListe.get(i).getModel()+" "+ gearListe.get(i).getCategory());
        }
        this.gearList.setItems(new FilteredList<>(FXCollections.observableList(gear)));
        this.gearList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle (MouseEvent event){
                //TODO: ouvrir une nouvelle fenêtre
                //TODO: appeler facade avec findClintById() et afficher les informations du client dans la nouvelle fenêtre
                System.out.println("clicked on " + gearList.getSelectionModel().getSelectedItem());
            }

        });
    }
}
