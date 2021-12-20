package com.filmplanner.facades;


import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;

public class ShootFacade {
    private static ShootFacade instance;
    private final AbstractDAOFactory daoFactory;
    private ShootDAO userDAO;

    private ShootFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.userDAO = this.daoFactory.getShootDAO();
    }

    public static ShootFacade getInstance() {
        if (instance == null) {
            instance = new ShootFacade();
        }
        return instance;
    }
}
