package com.filmplanner.dao.postgre;

import com.filmplanner.dao.*;

public class PostgreDAOFactory extends AbstractDAOFactory {

    private static PostgreDAOFactory instance;
    private UserDAO userDAO;
    private ProjectDAO projectDAO;
    private ClientDAO clientDAO;
    private GearDAO gearDAO;
    private ShootDAO shootDAO;
    private LocationDAO locationDAO;

    private PostgreDAOFactory() {
    }

    /**
     * Gets the PostgreDAOFactory single instance.
     *
     * @return the PostgreDAOFactory single instance.
     */
    public static PostgreDAOFactory getInstance() {
        if (instance == null) {
            instance = new PostgreDAOFactory();
        }
        return instance;
    }

    /**
     * Gets the PostgreUserDAO. This function makes sure only one instance
     * of PostgreUserDAO can exist at the same time.
     *
     * @return the PostgreUserDAO instance
     */
    @Override
    public UserDAO getUserDAO() {
        if (this.userDAO == null) {
            this.userDAO = new PostgreUserDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.userDAO;
    }

    /**
     * Gets the PostgreProjectDAO. This function makes sure only one instance
     * of PostgreUserDAO can exist at the same time.
     *
     * @return the PostgreUserDAO instance
     */
    @Override
    public ProjectDAO getProjectDAO() {
        if (this.projectDAO == null) {
            this.projectDAO = new PostgreProjectDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.projectDAO;
    }

    public ClientDAO getClientDAO() {
        if (clientDAO == null) {
            this.clientDAO = new PostgreClientDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.clientDAO;
    }

    @Override
    public ShootDAO getShootDAO() {
        if (shootDAO == null) {
            this.shootDAO = new PostgreShootDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.shootDAO;
    }

    public LocationDAO getLocationDAO(){
        if (locationDAO == null) {
            this.locationDAO = new PostgreLocationDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.locationDAO;
    }

    /**
     * Gets the PostgreGearDAO. This function makes sure only one instance
     * of PostgreGearDAO can exist at the same time.
     *
     * @return the PostgreGearDAO instance
     */
    @Override
    public GearDAO getGearDAO() {
        if (gearDAO == null) {
            this.gearDAO = new PostgreGearDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.gearDAO;
    }
}
