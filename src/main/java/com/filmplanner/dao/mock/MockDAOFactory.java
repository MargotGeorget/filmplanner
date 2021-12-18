package com.filmplanner.dao.mock;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.UserDAO;

public class MockDAOFactory extends AbstractDAOFactory {

    private static MockDAOFactory instance;
    private UserDAO userDAO;
    private GearDAO gearDAO;

    private MockDAOFactory() {
    }

    /**
     * Gets the MockDAOFactory single instance.
     *
     * @return the MockDAOFactory single instance.
     */
    public static MockDAOFactory getInstance() {
        if (instance == null) {
            instance = new MockDAOFactory();
        }
        return instance;
    }

    /**
     * Gets the MockUserDAO instance. This function makes sure only one instance
     * of MockUserDAO can exist at the same time.
     *
     * @return the MockUserDAO instance
     */
    @Override
    public UserDAO getUserDAO() {
        if (this.userDAO == null) {
            this.userDAO = new MockUserDAO();
        }
        return this.userDAO;
    }
    /**
     * Gets the MockGearDAO instance. This function makes sure only one instance
     * of MockGearDAO can exist at the same time.
     *
     * @return the MockGearDAO instance
     */
    @Override
    public GearDAO getGearDAO(){
        if (this.gearDAO == null) {
            //this.gearDAO = new MockGearDAO(); TODO à faire
        }
        return this.gearDAO;
    }
}
