package com.filmplanner.controllers.client;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidValuesClientException;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

//TODO: add JavaDoc
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

    //Client façade pour gérer lien avec la business logic
    private ClientFacade clientFacade;

    public ClientFormController() {
        this.clientFacade = ClientFacade.getInstance();
    }


    /**
     * Retrieves the information entered by the user and creates the associated client
     * If the creation is completed, display an alert and return to the home page of the client view
     *
     * @throws IOException
     */
    public void createClient() throws IOException {

        //Recovery of data entered
        String companyName = companyNameInput.getText().trim();
        String refereeName = refereeNameInput.getText();
        String refereeEmail = refereeEmailInput.getText().trim();
        String refereeTel = refereeTelInput.getText();
        String description = descriptionInput.getText();

        try {
            //Create client variable
            Client newClient = new Client(companyName, description, refereeName, refereeEmail, refereeTel);

            //Sends client to create to facade
            long idClient = clientFacade.create(newClient);
            newClient.setIdClient(idClient);

            if (idClient != -1) {
                Alert message = new Alert(Alert.AlertType.CONFIRMATION);
                message.setContentText("Operation done successfully\nClient " + newClient.getCompanyName() + " added!");
                message.show();
                App.setRoot("views/client/clientView");
            } else {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Error in client creation\nClient " + newClient.getCompanyName() + "not added!\nError with database");
                message.show();
            }
        } catch (InvalidValuesClientException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Creation cancelled\nError in client input: " + e.getMessage() + "!");
            message.show();
        }

    }

    /**
     * Return to the home page of the client view
     *
     * @throws IOException
     */
    public void cancelCreation() throws IOException {
        App.setRoot("views/client/clientView");
    }

}
