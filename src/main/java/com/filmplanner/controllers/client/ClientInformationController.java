package com.filmplanner.controllers.client;

import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ClientInformationController implements Initializable {

    @FXML
    private Label companyName;

    @FXML
    private Label companyDescription;

    @FXML
    private Label refereeName;

    @FXML
    private Label refereeEmail;

    @FXML
    private Label refereeTel;

    private Client client;

    public ClientInformationController(Client client) {
        this.client = client;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.companyName.setText(client.getCompanyName());
        this.companyDescription.setText(client.getDescription());
        this.refereeName.setText(client.getRefereeName());
        this.refereeEmail.setText(client.getRefereeEmail());
        this.refereeTel.setText(client.getRefereeTel());
    }
}
