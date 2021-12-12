package com.filmplanner.dao.postgre;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.UserDAO;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.SQLException;

public class PostgreDAOFactory extends AbstractDAOFactory {

    private static PostgreDAOFactory instance;
    private UserDAO userDAO;
    private GearDAO gearDAO;

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
        if (userDAO == null) {
            this.userDAO = new PostgreUserDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.userDAO;
    }
    public GearDAO getGearDAO() {
        if (gearDAO == null) {
            this.gearDAO = new PostgreGearDAO(PostgreConnection.getInstance().getConnection());
        }
        return this.gearDAO;
    }
}
