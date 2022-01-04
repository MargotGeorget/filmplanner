package com.filmplanner.dao;

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

    /**
     * Gets the GearDAO. This function should maks sure only one instance
     * of a concrete GearDAO can exist at the same time.
     *
     * @return a concrete GearDAO instance
     */
    abstract public GearDAO getGearDAO();

    /**
     * Gets the ClientDAO. This function should maks sure only one instance
     * of a concrete ClientDAO can exist at the same time.
     *
     * @return a concrete ClientDAO instance
     */
    abstract public ClientDAO getClientDAO();

    /**
     * Gets the ShootDAO. This function should maks sure only one instance
     * of a concrete ShootDAO can exist at the same time.
     *
     * @return a concrete ShootDAO instance
     */
    abstract public ShootDAO getShootDAO();

    /**
     * Gets the LocationDAO. This function should maks sure only one instance
     * of a concrete LocationDAO can exist at the same time.
     *
     * @return a concrete LocationDAO instance
     */
    abstract public LocationDAO getLocationDAO();

    /**
     * Gets the RoleDAO. This function should maks sure only one instance
     * of a concrete RoleDAO can exist at the same time.
     *
     * @return a concrete RoleDAO instance
     */
    abstract public RoleDAO getRoleDAO();
}
