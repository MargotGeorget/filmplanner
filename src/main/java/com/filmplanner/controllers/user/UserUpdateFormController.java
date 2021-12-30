package com.filmplanner.controllers.user;

import com.filmplanner.App;
import com.filmplanner.facades.UserFacade;
import com.filmplanner.models.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserUpdateFormController implements Initializable {

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField userEmailInput;

    @FXML
    private TextField userPhoneInput;

    @FXML
    private TextField userPasswordInput;

    @FXML
    private CheckBox isAdminInput;
    User user;
    Stage stage;


    private final UserFacade userFacade;
    public UserUpdateFormController(User user, Stage stage) {
        this.userFacade = UserFacade.getInstance();
        this.user = user;
        this.stage = stage;
    }

    /**
     * @throws IOException
     * updates the user in the database with the values provided by the user
     * The user has to set the password again
     */
    public void updateUser() throws IOException {
        //TODO: Manage empty input

        //Getting text input
        String userName = userNameInput.getText().trim();
        String userEmail = userEmailInput.getText().trim();
        String userPhone = userPhoneInput.getText();
        String userPassword = userPasswordInput.getText();
        Boolean userIsAdmin = isAdminInput.isSelected();

        if (userName.equals("") ||userEmail.equals("") ||userPassword.equals("")) {
// Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Field error");
            alert.setHeaderText("Not all fields are filled");
            alert.setContentText("Please fill all the fields (except phone number)");
            alert.show();
        }else {
            //User  variable creation
            User newUser = new User(userName, userEmail,userPassword,userPhone,userIsAdmin);
            System.out.println(newUser.getName() + " - " + newUser.getEmail() + " - " + newUser.getPhoneNumber() + " - " + newUser.getPassword());
            //Calls the facade to insert in database
            long done = userFacade.update(user.getId(), newUser);

            //feedback

            if(done != 0){
                Alert addedUser = new Alert(Alert.AlertType.INFORMATION);
                addedUser.setContentText("Operation done successfully\nUser " + newUser.getName() + " updated!");
                addedUser.show();
                stage.close();
            }

        }


    }

    public void cancelUpdate() throws IOException {
        stage.close();
    }


    /**
     * @param url
     * @param resourceBundle
     * Set fields with previous values (except password)
     *
     */
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.userNameInput.setText(user.getName());
        } catch(Exception e ) {
            System.out.println("No field for Name");
        }
        try {
            this.userEmailInput.setText(user.getEmail());
        } catch(Exception e ) {
            System.out.println("No field for Email");
        }
        try {
            this.userPhoneInput.setText(user.getPhoneNumber());
        } catch(Exception e ) {
            System.out.println("No field for PhoneNumber");
        }
        try {
            this.isAdminInput.setSelected(user.isAdmin());
        } catch(Exception e ) {
            System.out.println("No field for isAdmin");
        }

    }
}
