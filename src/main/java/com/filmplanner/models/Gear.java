package com.filmplanner.models;

import com.filmplanner.exceptions.InvalidInputException;

public class Gear {
    private String model;
    private String category;
    private String serialNumber;

    public Gear(String serialNumber, String model, String category) {
        this.model = model;
        this.category = category;;
        this.serialNumber = serialNumber;;
    }
    public Gear(String serialNumber, String model, String category,boolean verif) throws InvalidInputException {
        if(serialNumber=="" || model=="" || category==""){
            throw new InvalidInputException("Erreur");
        }else {
            this.model = model;
            this.category = category;;
            this.serialNumber = serialNumber;;
        }
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
