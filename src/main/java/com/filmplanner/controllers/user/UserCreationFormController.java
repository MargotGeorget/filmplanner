package com.filmplanner.controllers.user;
import com.filmplanner.App;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.facades.UserFacade;
import com.filmplanner.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;

import java.io.IOException;
public class UserCreationFormController {

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



    private final UserFacade userFacade;
    public UserCreationFormController() {
        this.userFacade = UserFacade.getInstance();
    }

    public void createUser() throws IOException {
        //TODO: Manage empty input

        //Getting text input
        String userName = userNameInput.getText().trim();
        String userEmail = userEmailInput.getText().trim();
        String userPhone = userPhoneInput.getText();
        String userPassword = userPasswordInput.getText();
        Boolean isAdmin = isAdminInput.isSelected();
        if (userName.equals("") ||userEmail.equals("") ||userPassword.equals("")) {
// Nothing selected.
            //TODO create verif
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Field error");
            alert.setHeaderText("Not all fields are filled");
            alert.setContentText("Please fill all the fields (except phone number)");
            alert.show();
        }else {

            //User  variable creation
            try {
                User newUser = new User(userName, userEmail, userPassword, userPhone,isAdmin,true);
                System.out.println(newUser.getName() + " - " + newUser.getEmail() + " - " + newUser.getPhoneNumber() + " - " + newUser.getPassword()+ " - " + newUser.isAdmin());
                //Calls the facade to insert in database
                long idUser = userFacade.create(newUser);

                //feedback
                if (idUser != 0) {
                    Alert addedUser = new Alert(Alert.AlertType.INFORMATION);
                    addedUser.setContentText("Operation done successfully\nUser " + newUser.getName() + " added!");
                    addedUser.show();
                    App.setRoot("views/user/userView");
                }else {
                    Alert addedUser = new Alert(Alert.AlertType.WARNING);
                    addedUser.setContentText("Error ! Please verify that the email is not already used");
                    addedUser.show();

                }
            } catch (InvalidInputException e) {
                Alert message = new Alert(Alert.AlertType.ERROR);
                message.setContentText("Creation cancelled\nError in user input: " + e.getMessage() + "!");
                message.show();
            }


        }
    }

    public void cancelCreation() throws IOException {
        App.setRoot("views/user/userView");
    }


}
