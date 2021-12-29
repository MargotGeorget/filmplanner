package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.RoleDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Role;

import java.util.ArrayList;

public class RoleFacade {
    private static RoleFacade instance;
    private final AbstractDAOFactory daoFactory;
    private final RoleDAO roleDAO;

    /**
     * Instantiates a RoleFacade. This facades gives the UI access to the role business logic.
     */
    private RoleFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.roleDAO = this.daoFactory.getRoleDAO();
    }

    /**
     * Gets the single RoleFacade instance.
     *
     * @return the single RoleFacade instance.
     */
    public static RoleFacade getInstance() {
        if (instance == null) {
            instance = new RoleFacade();
        }
        return instance;
    }

    /**
     * @param role to create
     * @return true if it's done else false
     */
    public boolean createRole(Role role) {
        return roleDAO.createRole(role);
    }

    /**
     * @param role to delete
     * @return true if it's done else false
     */
    public boolean deleteRole(Role role) {
        return roleDAO.deleteRole(role.getName());
    }

    /**
     * @return in a ArrayList all roles created
     */
    public ArrayList<Role> findAllRole() {
        return roleDAO.findAllRole();
    }
}
