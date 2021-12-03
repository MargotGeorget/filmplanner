package com.filmplanner.models;

public class User {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;

    /**
     * Instantiates a User.
     * @param name the user's name
     * @param email the user's email
     */
    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Instantiates a User.
     * @param name the user's name
     * @param email the user's email
     * @param password the user's password
     */
    public User(String name, String email, String password, String phoneNumber) {
        this(name, email, phoneNumber);
        this.password = password;
    }


    /*
    Getters
     */

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() { return phoneNumber; }


    /*
    Setters
     */

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
