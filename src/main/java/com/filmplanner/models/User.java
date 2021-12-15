package com.filmplanner.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;
    private StringProperty phoneNumber;
    private IntegerProperty id;

    /**
     * Instantiates a User.
     *
     * @param name  the user's name
     * @param email the user's email
     */
    public User(String name, String email, String phoneNumber) {
        this.name = new SimpleStringProperty(name);
        this.email =  new SimpleStringProperty(email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    /**
     * Instantiates a User.
     *
     * @param name     the user's name
     * @param email    the user's email
     * @param password the user's password
     */
    public User(String name, String email, String password, String phoneNumber) {
        this(name, email, phoneNumber);
        this.password = new SimpleStringProperty(password);
    }
    public User(String name, String email, String password, String phoneNumber, int id) {
        this(name, email, phoneNumber);
        this.id = new SimpleIntegerProperty(id);
    }

    /*
    Getters
     */

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public int getId() {
        return id.get();
    }

    /*
    Setters
     */

    public void setName(String name) {
        this.name.set(name);
    }

    public void setEmail(String email) {
        this.email.set(email) ;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setId(int id) {this.id.set(id);}

    /*
    Property objects (useful with Tableview)
     */

    /**
     * @return StringProperty object
     */
    public StringProperty getNameProperty() {
        return name;
    }
    /**
     * @return StringProperty object
     */
    public StringProperty getEmailProperty() {
        return email;
    }
    /**
     * @return StringProperty object
     */
    public StringProperty getPasswordProperty() {
        return password;
    }
    /**
     * @return StringProperty object
     */
    public StringProperty getPhoneNumberProperty() {
        return phoneNumber;
    }
    /**
     * @return IntegerProperty object
     */
    public IntegerProperty getIDProperty() { return id;}


}
