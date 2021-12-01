package com.filmplanner.models;

public class User {
    private String name;
    private String email;
    private String password;

    /**
     * Instantiates a User.
     * @param name the user's name
     * @param email the user's email
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Instantiates a User.
     * @param name the user's name
     * @param email the user's email
     * @param password the user's password
     */
    public User(String name, String email, String password) {
        this(name, email);
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
}
