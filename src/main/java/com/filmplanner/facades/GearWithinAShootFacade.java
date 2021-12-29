package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.GearWithinAShootDAO;
import com.filmplanner.dao.LocationDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Gear;
import com.filmplanner.models.GearWithinAShoot;

import java.util.List;

public class GearWithinAShootFacade {
    private static GearWithinAShootFacade instance;
    private final AbstractDAOFactory daoFactory;
    private GearWithinAShootDAO gearWithinAShootDAO;

    private GearWithinAShootFacade() {
        this.daoFactory =  PostgreDAOFactory.getInstance();
        this.gearWithinAShootDAO = this.daoFactory.getGearWithinAShootDAO() ;
    }

    /**
     * Gets the single GearWithinAShootFacade instance.
     * @return the single GearWithinAShootFacade instance.
     */
    public static GearWithinAShootFacade getInstance() {
        if (instance == null) {
            instance = new GearWithinAShootFacade();
        }
        return instance;
    }

    public boolean create(GearWithinAShoot gearWithinAShoot){
        return this.gearWithinAShootDAO.create(gearWithinAShoot);
    }

    public GearWithinAShoot getOne(long idShoot, String idGear){
        return this.gearWithinAShootDAO.getOne(idShoot, idGear);
    }

    public List<Gear> getAllGearsWithinAShoot(long idShoot){
        return this.gearWithinAShootDAO.getAllGearsWithinAShoot(idShoot);
    }

    public boolean delete(long id){
        return this.gearWithinAShootDAO.delete(id);
    }
}
