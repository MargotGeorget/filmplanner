package com.filmplanner.controllers.paperwork;

import com.filmplanner.facades.PaperworkFacade;
import com.filmplanner.models.Paperwork;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class PaperworkEditInformationController implements Initializable {

    @FXML
    TextField paperworkDescription;

    @FXML
    Label filePath;

    // Attributes
    private PaperworkFacade paperworkFacade;
    private Paperwork paperwork;
    private Stage stage;

    public PaperworkEditInformationController(Paperwork paperwork, Stage stage) {
        this.paperworkFacade = PaperworkFacade.getInstance();
        this.paperwork = paperwork;
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.filePath.setText(this.paperwork.getDirectoryPath() + "/" + this.paperwork.getFileName());
        this.paperworkDescription.setText(this.paperwork.getDescription());
    }

    public void openFileChooser() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a paperwork");
        File file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            System.out.println("File " + file.getAbsolutePath());
        }
    }

    public void validateAction() {
        // TODO
    }
}
