package com.filmplanner.controllers.client;

import com.filmplanner.App;
import com.filmplanner.facades.ClientFacade;
import com.filmplanner.models.Client;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ClientViewController implements Initializable {

    @FXML
    private TextField searchInput;

    @FXML
    private ListView<Client> clientsList;

    private ClientFacade clientFacade;

    public ClientViewController() {
        this.clientFacade = ClientFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Retrieving clients from database
        List<Client> clients = clientFacade.findAll();

        //Display clients in list
        this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(clients)));

        //Function for displaying the information of a client when is selected by the user
        this.clientsList.setOnMouseClicked(event -> {
            //Retrieving selected client
            Client client = clientsList.getSelectionModel().getSelectedItem();

            //Client client = clientFacade.findById(clientSelected.getIdClient());

            //Create new stage to show client information
            Stage stage = new Stage();
            stage.setHeight(280);
            stage.setWidth(610);
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/client/clientInformation.fxml"));
            try {
                ClientInformationController controller = new ClientInformationController(client, stage);
                fxmlLoader.setController(controller);
                Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        });
    }

    /**
     * Change the current page to the add client form page
     *
     * @throws IOException
     */
    public void moveToCreateClient() throws IOException {
        App.setRoot("views/client/clientForm");
    }


}
