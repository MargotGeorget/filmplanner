package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.RoleDAO;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Role;

public class RoleFacade {
    private static RoleFacade instance;
    private final AbstractDAOFactory daoFactory;
    private RoleDAO roleDAO;

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
    public boolean createRole(Role role){
        return roleDAO.createRole(role);
    }
    public boolean deleteRole(Role role){
        return roleDAO.deleteRole(role.getName());
    }
}
