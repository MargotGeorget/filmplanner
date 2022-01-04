package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Gear;

import java.util.ArrayList;

public class GearFacade {
    private static GearFacade instance;
    private final AbstractDAOFactory daoFactory;
    private final GearDAO gearDAO;


    private GearFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.gearDAO = this.daoFactory.getGearDAO();
    }

    /**
     * GearFacade is a singleton
     * Get an instance of GearFacade
     * @return the single GearFacade instance.
     */
    public static GearFacade getInstance() {
        if (instance == null) {
            instance = new GearFacade();
        }
        return instance;
    }

    /**
     * @return All Gear in database
     */
    public ArrayList<Gear> getAllGear() {
        return this.gearDAO.findAllGear();
    }

    /**
     *
     * @param id of a gear to delete in database
     * @return true if it's done else false
     */
    public boolean delete(String id) {
        return this.gearDAO.deleteGear(id);
    }

    /**
     * @param gear to create in database
     */
    public void create(Gear gear) {
        this.gearDAO.createGear(gear);
    }

    /**
     *
     * @param serialnumber of a gear to update in database
     * @param updateGear the new Gear parameters
     * @return true if it's done else false
     */
    public boolean update(String serialnumber, Gear updateGear) {
        return this.gearDAO.updateGear(serialnumber, updateGear);

    }
}


