package com.filmplanner.controllers;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private TextField searchInput;

    @FXML
    private ListView<Pair<String, String>> clientsList;

    public ClientController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Récupérer les clients à partir de la base de données avec Facade

        ArrayList<Pair<String, String>> pairs = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            pairs.add(new Pair<>("Client " + i, "Adress client"));
        }
        this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(pairs)));
        this.clientsList.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                //TODO: ouvrir une nouvelle fenêtre
                //TODO: appeler facade avec findClintById() et afficher les informations du client dans la nouvelle fenêtre
                System.out.println("clicked on " + clientsList.getSelectionModel().getSelectedItem());
            }

        });
    }


}