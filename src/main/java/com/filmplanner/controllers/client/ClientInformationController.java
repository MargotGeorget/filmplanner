package com.filmplanner.controllers.client;

import com.filmplanner.App;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
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

    private ClientFacade clientFacade;

    private Stage stage;

    public ClientInformationController(Client client, Stage stage) {
        this.client = client;
        this.clientFacade = ClientFacade.getInstance();
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

    public void editClientAction() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/client/clientFormUpdate.fxml"));
        try {
            ClientFormUpdateController controller = new ClientFormUpdateController(client,this.clientFacade, this.stage);
            fxmlLoader.setController(controller);
            stage.setScene(new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteClientAction() throws IOException {
        this.clientFacade.delete(client.getIdClient());
        //TODO: verif
        //Display alert
        Alert addedClient = new Alert(Alert.AlertType.CONFIRMATION);
        addedClient.setContentText("Operation done successfully\nClient " + this.client.getCompanyName() + " deleted!");
        addedClient.show();

        //Reload listView and close update stage
        App.setRoot("views/client/clientView");
        this.stage.close();
    }
}
