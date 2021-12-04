package com.filmplanner.dao;

public abstract class AbstractDAOFactory {

    /**
     * Gets the UserDAO. This function should maks sure only one instance
     * of a concrete UserDAO can exist at the same time.
     *
     * @return a concrete UserDAO instance
     */
    abstract public UserDAO getUserDAO();
}
