package com.filmplanner.facades;


import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Role;
import com.filmplanner.models.Shoot;
import com.filmplanner.models.User;
import com.filmplanner.models.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShootFacade {
    private static ShootFacade instance;
    private final AbstractDAOFactory daoFactory;
    private ShootDAO shootDAO;
    private UserDAO userDAO;

    private ShootFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.shootDAO = this.daoFactory.getShootDAO();
        this.userDAO = this.daoFactory.getUserDAO();
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

    public boolean updateShoot(Shoot shoot) throws InvalidInputException {
        return shootDAO.update(shoot);
    }

    public boolean delete(long idShoot){
        return this.shootDAO.delete(idShoot);
    }

    public List<User> allUserAvailableForDate(String date) {
        return this.userDAO.allUserAvailableForDate(date);
    }

    public boolean addUserToAShoot(Shoot shoot, User user, Role role) {
        return this.shootDAO.addUserInAShoot(shoot, user, role);
    }

    public boolean deleteUserInAShoot(Shoot shoot, User user) {
        return this.shootDAO.deleteUserInAShoot(shoot, user);
    }

    public List<UserRole> allUserInAShoot(Shoot shoot) {
        HashMap<User, Role> a = this.shootDAO.allUserInAShoot(shoot);
        ArrayList<UserRole> resultat = new ArrayList<>();
        for (User e : a.keySet()) {
            resultat.add(new UserRole(e, a.get(e)));

        }
        return resultat;
    }
}
