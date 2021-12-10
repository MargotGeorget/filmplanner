package com.filmplanner.controllers.client;

import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private TextArea desciptionInput;

    private ClientFacade clientFacade;

    public ClientFormController() {
        this.clientFacade = ClientFacade.getInstance();
    }

    public void createClient(){
        //TODO: Manage empty input

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
