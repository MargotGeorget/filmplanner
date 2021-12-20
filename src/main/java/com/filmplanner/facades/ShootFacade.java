package com.filmplanner.facades;


import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Shoot;

import java.util.List;

public class ShootFacade {
    private static ShootFacade instance;
    private final AbstractDAOFactory daoFactory;
    private ShootDAO shootDAO;

    private ShootFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.shootDAO = this.daoFactory.getShootDAO();
    }

    public static ShootFacade getInstance() {
        if (instance == null) {
            instance = new ShootFacade();
        }
        return instance;
    }

    public long createShoot(Shoot shoot){
        return shootDAO.create(shoot);
    }

    public List<Shoot> findAllShootInProject(long idProject){
        return shootDAO.findAllShootInProject(idProject);
    }
}
