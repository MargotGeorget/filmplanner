package com.filmplanner.dao;

import com.filmplanner.facades.ShootFacade;

public abstract class AbstractDAOFactory {

    /**
     * Gets the UserDAO. This function should maks sure only one instance
     * of a concrete UserDAO can exist at the same time.
     *
     * @return a concrete UserDAO instance
     */
    abstract public UserDAO getUserDAO();

    /**
     * Gets the ProjectDAO. This function should maks sure only one instance
     * of a concrete ProjectDAO can exist at the same time.
     *
     * @return a concrete ProjectDAO instance
     */
    abstract public ProjectDAO getProjectDAO();

    abstract public GearDAO getGearDAO();

    abstract public ClientDAO getClientDAO();

    abstract public ShootDAO getShootDAO();

    abstract public LocationDAO getLocationDAO();

    abstract public GearWithinAShootDAO getGearWithinAShootDAO();
}
