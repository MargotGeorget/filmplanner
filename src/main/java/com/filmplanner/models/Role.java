package com.filmplanner.models;

import com.filmplanner.exceptions.InvalidInputException;

public class Role {
    private String name;

    public Role( String name) {
        this.name = name;
    }
    public Role( String name,boolean verif) throws InvalidInputException {
        if(name==""){
            throw new InvalidInputException("Erreur");
        }else {
            this.name = name;
        }

    }

    public String getName() {
        return name;
    }
}
