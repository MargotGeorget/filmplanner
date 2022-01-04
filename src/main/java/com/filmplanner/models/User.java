package com.filmplanner.models;

import com.filmplanner.exceptions.InvalidInputException;
import javafx.beans.property.*;
import utils.ValidationUtils;

public class User {
    private StringProperty name;
    private StringProperty email;
    private StringProperty password;
    private StringProperty phoneNumber;
    private LongProperty id;
    private BooleanProperty isAdmin;

    //CONSTRUCTORS WITHOUT VERIFICATION
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
    public User(String name, String email, String password, String phoneNumber,boolean isAdmin) {
        this(name, email, phoneNumber);
        this.password = new SimpleStringProperty(password);
        this.isAdmin =new SimpleBooleanProperty(isAdmin);
    }

    public User(Long id,String name, String email, String password, String phoneNumber,boolean isAdmin) {
        this(name, email,password, phoneNumber,isAdmin);
        this.id = new SimpleLongProperty(id);

    }


    //CONSTRUCTORS WITH VERIFICATION

    /**
     * Instantiates a User.
     *
     * @param name  the user's name
     * @param email the user's email
     */
    public User(String name, String email, String phoneNumber,Boolean verif) throws InvalidInputException  {
        this.setName(name);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    /**
     * Instantiates a User.
     *
     * @param name     the user's name
     * @param email    the user's email
     * @param password the user's password
     */
    public User(String name, String email, String password, String phoneNumber,boolean isAdmin,Boolean verif) throws InvalidInputException  {
        this(name, email, phoneNumber, true);
        this.password = new SimpleStringProperty(password);
        this.isAdmin =new SimpleBooleanProperty(isAdmin);
    }


    public User(Long id,String name, String email, String password, String phoneNumber,boolean isAdmin,Boolean verif) throws InvalidInputException {
        this(name, email,password, phoneNumber,isAdmin,true);
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
        return isAdmin.get();
    }
    /*
    Setters
     */

    public void setName(String name)  throws InvalidInputException{
        if (!ValidationUtils.isStringWithoutSpecialSymbol(name)) {
            throw new InvalidInputException("User name contains special symbol!");
        } else if (name.length() < 2) {
            throw new InvalidInputException("User name too short!");
        } else if (name.length() > 30) {
            throw new InvalidInputException("User name too long!");
        } else {
            this.name.set(name);
        }

    }

    public void setEmail(String email) throws InvalidInputException {
        if (!ValidationUtils.isEmail(email)) {
            throw new InvalidInputException("User email is not an email");
        } else {
            this.email.set(email) ;
        }

    }

    public void setPassword(String password) throws InvalidInputException {
        if (password.length() < 3) {
            throw new InvalidInputException("The password is too short!");
        } else if (password.length() > 30) {
            throw new InvalidInputException("The password is too long!");
        } else {
            this.password.set(password);
        }

    }

    public void setPhoneNumber(String phoneNumber)throws InvalidInputException  {
        if (!ValidationUtils.isPhoneNumber(phoneNumber)) {
            throw new InvalidInputException("User tel is not a phone number");
        } else {
            this.phoneNumber.set(phoneNumber);
        }

    }

    public void setId(long id) {
        this.id.set(id);
    }

    public void setAdmin(boolean admin) {
        this.isAdmin.set(admin);
    }

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
     * @return LongProperty object
     */
    public LongProperty getIDProperty() { return id;}
    /**
     * @return BooleanProperty object
     */
    public BooleanProperty getIsAdminProperty() { return isAdmin;}



    /*
    Methods
     */

    @Override
    public String toString() {
        return "Email : " + this.getEmail() + ", Name : " + this.getName() + " Phone number :" + this.getPhoneNumber();
    }
}
