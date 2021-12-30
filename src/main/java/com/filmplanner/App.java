package com.filmplanner;

import com.filmplanner.dao.postgre.PostgreConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setWidth(800);
        stage.setHeight(500);
        scene = new Scene(loadFXML("views/home"), stage.getWidth(), stage.getHeight());
        scene = new Scene(loadFXML("views/user/userView"), stage.getWidth(), stage.getHeight());
        stage.setScene(scene);

        //TODO: Mettre icon
        //Image icon = new Image(getClass().getResourceAsStream("logo1.png"),true);
        //stage.getIcons().add(icon);
        stage.show();
    }

    /**
     * Sets the App scene root as the Parent loaded from the given FXML file.
     *
     * @param fxml the FXML file to load the Parent from
     * @throws IOException
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads a FXML file.
     *
     * @param fxml the FXML file to load.
     * @return the parent node of the scene loaded from the FXML file
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException {
        launch();
        System.out.println("Application fermée!");
        PostgreConnection.getInstance().getConnection().close();
    }
}