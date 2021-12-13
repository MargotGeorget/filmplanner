package com.filmplanner.controllers.client;

import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void updateAction(){
        //Recovery of data entered
        String companyName = this.companyName.getText().trim();
        String refereeName = this.refereeName.getText();
        String refereeEmail = this.refereeEmail.getText().trim();
        String refereeTel = this.refereeTel.getText();
        String description = this.companyDescription.getText();

        client.setAllInformation(companyName, description, refereeName, refereeEmail, refereeTel);
        clientFacade.update(this.client.getIdClient(), this.client);
    }


}