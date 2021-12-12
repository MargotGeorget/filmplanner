package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.exceptions.InvalidCredentialsException;
import com.filmplanner.exceptions.UserNotFoundException;
import com.filmplanner.models.Gear;
import com.filmplanner.models.User;

import java.util.ArrayList;

public class GearFacade {
    private static GearFacade instance;
    private final AbstractDAOFactory daoFactory;
    private GearDAO gearDAO;


    private GearFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.gearDAO = this.daoFactory.getGearDAO();
    }


    public static GearFacade getInstance() {
        if (instance == null) {
            instance = new GearFacade();
        }
        return instance;
    }
    public ArrayList<Gear> getAllGear(){
        return this.gearDAO.findAllGear();
    }
    /*


    public User login(String email, String password) throws InvalidCredentialsException, UserNotFoundException {
        User user = this.userDAO.find(email);
        if (user == null) {
            throw new UserNotFoundException("The email " + email + " is not registered");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new InvalidCredentialsException("Incorrect password.");
    }*/
}


