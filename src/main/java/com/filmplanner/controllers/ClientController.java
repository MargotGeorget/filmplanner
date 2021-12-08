package com.filmplanner.controllers;

import com.filmplanner.App;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientController implements Initializable {

    @FXML
    private TextField searchInput;

    @FXML
    private TextField companyNameInput;

    @FXML
    private TextField refereeNameInput;

    @FXML
    private TextField refereeEmailInput;

    @FXML
    private TextField refereeTelInput;

    @FXML
    private TextArea desciptionInput;

    @FXML
    private ListView<Pair<String, String>> clientsList;

    private ClientFacade clientFacade;

    public ClientController() {
        this.clientFacade = ClientFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Récupérer les clients à partir de la base de données avec Facade

//        List<Pair<String, String>> pairs = new ArrayList<>();
//        List<Client> clients = clientFacade.findAll();
//        for (Client c : clients) {
//            pairs.add(new Pair<>(c.getCompanyName(), c.getRefereeEmail()));
//        }
//        this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(pairs)));
//        this.clientsList.setOnMouseClicked(new EventHandler<MouseEvent>(){
//
//            @Override
//            public void handle (MouseEvent event){
//                //TODO: ouvrir une nouvelle fenêtre
//                //TODO: appeler facade avec findClintById() et afficher les informations du client dans la nouvelle fenêtre
//                System.out.println("clicked on " + clientsList.getSelectionModel().getSelectedItem());
//            }
//
//        });
    }

    public void moveToCreateClient() throws IOException {
        App.setRoot("views/clientForm");
    }

    public void createClient(){
        //Recovery of data entered
        String companyName = companyNameInput.getText().trim();
        String refereeName = refereeNameInput.getText();
        String refereeEmail = refereeEmailInput.getText().trim();
        String refereeTel = refereeTelInput.getText();
        String description = desciptionInput.getText();

        //Create client variable
        Client newClient = new Client(companyName, description, refereeName, refereeEmail, refereeTel);

        //Sends customer to create to facade
        //TODO: gérer création avec data base
        Client client = clientFacade.create(newClient);

        //TODO: add verif

        System.out.println(newClient.getCompanyName() + " - " + newClient.getRefereeName() + " - " + newClient.getRefereeEmail() + " - " + newClient.getRefereeTel() + " - " + newClient.getDescription());

    }



}
