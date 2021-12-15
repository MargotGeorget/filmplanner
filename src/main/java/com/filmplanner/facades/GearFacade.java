package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Gear;

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

    public boolean delete(String id){
        return this.gearDAO.deleteGear(id);
    }
    public void create(Gear gear){
        this.gearDAO.createGear(gear);
    }
    public boolean update(String serialnumber, Gear updateGear){
        return this.gearDAO.updateGear(serialnumber,updateGear);

    }
}


