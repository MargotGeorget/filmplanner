package com.filmplanner.dao.mock;

import com.filmplanner.dao.*;

public class MockDAOFactory extends AbstractDAOFactory {

    private static MockDAOFactory instance;
    private UserDAO userDAO;
    private ProjectDAO projectDAO;
    private GearDAO gearDAO;
    private RoleDAO roleDAO;

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

    //TODO : implement method for unitary test
    @Override
    public ClientDAO getClientDAO() {
        return null;
    }

    @Override
    public ShootDAO getShootDAO() {
        //TODO : implement method
        return null;
    }

    @Override
    public LocationDAO getLocationDAO() {
        return null;
    }

    /**
     * Gets the MockRoleDAO instance. This function makes sure only one instance
     * of MockRoleDAO can exist at the same time.
     *
     * @return the MockRoleDAO instance
     */

    @Override
    public RoleDAO getRoleDAO() {
        if (this.roleDAO == null) {
            this.roleDAO = new MockRoleDAO();
        }
        return this.roleDAO;
    }

    /**
     * Gets the MockProjectDAO instance. This function makes sure only one instance
     * of MockProjectDAO can exist at the same time.
     *
     * @return the MockProjectDAO instance
     */
    @Override
    public ProjectDAO getProjectDAO() {
        if (this.projectDAO == null) {
            this.projectDAO = new MockProjectDAO();
        }
        return this.projectDAO;
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

    @Override
    public PaperworkDAO getPaperworkDAO() {
        return null;
    }
}
