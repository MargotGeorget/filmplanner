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
    private ListView<Pair<String, String>> clientsList;

    private ClientFacade clientFacade;

    public ClientViewController() {
        this.clientFacade = ClientFacade.getInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //TODO: Récupérer les clients à partir de la base de données avec Facade

        List<Pair<String, String>> pairs = new ArrayList<>();
        List<Client> clients = clientFacade.findAll();
        for (Client c : clients) {
            pairs.add(new Pair<>(c.getCompanyName(), c.getRefereeEmail()));
        }
        this.clientsList.setItems(new FilteredList<>(FXCollections.observableList(pairs)));
        this.clientsList.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle (MouseEvent event){
                System.out.println("clicked on " + clientsList.getSelectionModel().getSelectedItem());

                //TODO: appeler facade avec findClintById() et afficher les informations du client dans la nouvelle fenêtre
                Client client = new Client("ndmvisuals", "Une super company pour faire des vidéos", "Nathan Djian-Martin", "nathan.djianmartin@gmail.com", "0203785412" );

                //Create new stage
                Stage stage = new Stage();
                stage.setHeight(300);
                stage.setWidth(610);
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/client/clientInformation.fxml"));
                try {
                    ClientInformationController controller = new ClientInformationController(client);
                    fxmlLoader.setController(controller);
                    Scene scene = new Scene(fxmlLoader.load(), stage.getWidth(),stage.getHeight());
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stage.show();
            }

        });
    }

    public void moveToCreateClient() throws IOException {
        App.setRoot("views/client/clientForm");
    }


}
