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
     * @return
     */
    public static GearFacade getInstance() {
        if (instance == null) {
            instance = new GearFacade();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public ArrayList<Gear> getAllGear() {
        return this.gearDAO.findAllGear();
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean delete(String id) {
        return this.gearDAO.deleteGear(id);
    }

    /**
     *
     * @param gear
     */
    public void create(Gear gear) {
        this.gearDAO.createGear(gear);
    }

    /**
     *
     * @param serialnumber
     * @param updateGear
     * @return
     */
    public boolean update(String serialnumber, Gear updateGear) {
        return this.gearDAO.updateGear(serialnumber, updateGear);

    }
}


