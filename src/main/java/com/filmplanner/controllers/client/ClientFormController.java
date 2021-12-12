package com.filmplanner.controllers.client;

import com.filmplanner.App;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ClientFormController {

    @FXML
    private TextField companyNameInput;

    @FXML
    private TextField refereeNameInput;

    @FXML
    private TextField refereeEmailInput;

    @FXML
    private TextField refereeTelInput;

    @FXML
    private TextArea descriptionInput;

    private ClientFacade clientFacade;

    public ClientFormController() {
        this.clientFacade = ClientFacade.getInstance();
    }

    public void createClient() throws IOException {
        //TODO: Manage empty input

        //Recovery of data entered
        String companyName = companyNameInput.getText().trim();
        String refereeName = refereeNameInput.getText();
        String refereeEmail = refereeEmailInput.getText().trim();
        String refereeTel = refereeTelInput.getText();
        String description = descriptionInput.getText();

        //Create client variable
        Client newClient = new Client(companyName, description, refereeName, refereeEmail, refereeTel);

        //Sends client to create to facade
        long idClient = clientFacade.create(newClient);

        //TODO: add verif
        System.out.println(newClient.getCompanyName() + " - " + newClient.getRefereeName() + " - " + newClient.getRefereeEmail() + " - " + newClient.getRefereeTel() + " - " + newClient.getDescription());
        if(idClient!=-1){
            Alert addedClient = new Alert(Alert.AlertType.CONFIRMATION);
            addedClient.setContentText("Operation done successfully\nClient " + newClient.getCompanyName() + " added!");
            addedClient.show();
            App.setRoot("views/client/clientView");
        }

    }

    public void cancelCreation() throws IOException {
        App.setRoot("views/client/clientView");
    }

}
