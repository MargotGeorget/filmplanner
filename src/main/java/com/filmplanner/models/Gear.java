package com.filmplanner.models;

public class Gear {
    private String model;
    private String category;
    private String serialNumber;

    public Gear(String serialNumber, String model, String category) {
        this.model = model;
        this.category = category;;
        this.serialNumber = serialNumber;;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
