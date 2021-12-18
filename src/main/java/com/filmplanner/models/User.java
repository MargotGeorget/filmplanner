package com.filmplanner.models;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;


    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(Long id, String name, String email, String phoneNumber, String password) {
        this(name, email, phoneNumber);
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getId() {
        return id;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    /*
    Methods
     */

    @Override
    public String toString() {
        return this.id + ": " + this.email + " " + this.name + " " + this.phoneNumber;
    }
}
