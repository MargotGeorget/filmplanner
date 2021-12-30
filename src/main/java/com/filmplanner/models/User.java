package com.filmplanner.models;

import javafx.beans.property.*;

public class User {
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;
    private StringProperty phoneNumber;
    private LongProperty id;
    private boolean isAdmin;

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
    public User(Long id,String name, String email, String password, String phoneNumber) {
        this(name, email,password, phoneNumber);
        this.id = new SimpleLongProperty(id);

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

    public Long getId() {
        return id.get();
    }

    public boolean isAdmin() {
        return isAdmin;
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

    public void setId(long id) {this.id.set(id);}

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
    public LongProperty getIDProperty() { return id;}

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /*
    Methods
     */

    @Override
    public String toString() {
        return this.id + ": " + this.email + " " + this.name + " " + this.phoneNumber;
    }
}
