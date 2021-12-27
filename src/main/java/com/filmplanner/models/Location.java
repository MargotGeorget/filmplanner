package com.filmplanner.models;

public class Location {
    //Attributes
    private long id;
    private int streetNumber;
    private String street;
    private String city;
    private String zipCode;

    //Constructor:
    public Location(int streetNumber, String street, String city, String zipCode) {
        this.setStreetNumber(streetNumber);
        this.setStreet(street);
        this.setCity(city);
        this.setZipCode(zipCode);
    }

    //Getters and setters:
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
