package com.filmplanner.controllers.client;

import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientFormUpdateController implements Initializable {

    @FXML
    private TextField companyName;

    @FXML
    private TextArea companyDescription;

    @FXML
    private TextField refereeName;

    @FXML
    private TextField refereeEmail;

    @FXML
    private TextField refereeTel;

    private Client client;

    private ClientFacade clientFacade;

    private Stage stage;

    public ClientFormUpdateController(Client client, ClientFacade clientFacade, Stage stage) {
        this.client = client;
        this.clientFacade = clientFacade;
        this.stage = stage;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.companyName.setText(client.getCompanyName());
        this.companyDescription.setText(client.getDescription());
        this.refereeName.setText(client.getRefereeName());
        this.refereeEmail.setText(client.getRefereeEmail());
        this.refereeTel.setText(client.getRefereeTel());

    }

    /**
     * Retrieves the information entered by the user and updates the associated client
     * If the modification is completed, display an alert and return to the home page of the client view
     * If the information given by the user is wrong, display an alert and stays on this page
     * @throws IOException
     */
    public void updateAction() throws IOException {
        //Recovery of data entered
        String companyName = this.companyName.getText().trim();
        String refereeName = this.refereeName.getText();
        String refereeEmail = this.refereeEmail.getText().trim();
        String refereeTel = this.refereeTel.getText();
        String description = this.companyDescription.getText();

        try {
            client.setAllInformation(companyName, description, refereeName, refereeEmail, refereeTel);
            clientFacade.update(this.client.getIdClient(), this.client);

            //Display alert
            Alert addedClient = new Alert(Alert.AlertType.CONFIRMATION);
            addedClient.setContentText("Operation done successfully\nClient " + this.client.getCompanyName() + " updated!");
            addedClient.show();

            //Reload listView and close update stage
            App.setRoot("views/client/clientView");
            this.stage.close();
        } catch (InvalidInputException e) {
            Alert message = new Alert(Alert.AlertType.ERROR);
            message.setContentText("Modification cancelled\nError in client input: " + e.getMessage() + "!");
            message.show();
        }

}

    /**
     * Return to the home page of the client view
     *
     * @throws IOException
     */
    public void cancelAction() throws IOException {
        App.setRoot("views/client/clientView");
        this.stage.close();
    }


}
