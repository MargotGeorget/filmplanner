package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Gear;
import com.filmplanner.models.GearWithinAShoot;

import java.util.List;

public class GearWithinAShootFacade {
    private static GearWithinAShootFacade instance;
    private final AbstractDAOFactory daoFactory;
    private ShootDAO shootDAO;

    private GearWithinAShootFacade() {
        this.daoFactory =  PostgreDAOFactory.getInstance();
        this.shootDAO = this.daoFactory.getShootDAO() ;
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

    public boolean create(GearWithinAShoot gearWithinAShoot) throws InvalidInputException {
        return this.shootDAO.createGearWithinAShoot(gearWithinAShoot);
    }

    public GearWithinAShoot getOne(long idShoot, String idGear){
        return this.shootDAO.getOneGearsWithinAShoot(idShoot, idGear);
    }

    public List<Gear> getAllGearsWithinAShoot(long idShoot){
        return this.shootDAO.getAllGearsWithinAShoot(idShoot);
    }

    public boolean delete(long id){
        return this.shootDAO.deleteGearWithinAShoot(id);
    }
}
