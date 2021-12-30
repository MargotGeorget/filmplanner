package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.LocationDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Location;

public class LocationFacade {
    private static LocationFacade instance;
    private final AbstractDAOFactory daoFactory;
    private LocationDAO locationDAO;

    private LocationFacade() {
        this.daoFactory =  PostgreDAOFactory.getInstance();
        this.locationDAO = this.daoFactory.getLocationDAO() ;
    }

    /**
     * Gets the single LocationFacade instance.
     * @return the single LocationFacade instance.
     */
    public static LocationFacade getInstance() {
        if (instance == null) {
            instance = new LocationFacade();
        }
        return instance;
    }

    //Functions:
    /**
     * Create a location in the datasource
     * @param newLocation
     * @return long : new location's id
     */
    public long create(Location newLocation){
        return locationDAO.create(newLocation);
    }

    public boolean update(Location location){
        return locationDAO.update(location);
    }
}
