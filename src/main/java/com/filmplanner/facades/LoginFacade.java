package com.filmplanner.facades;

import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreUserDAO;
import com.filmplanner.models.User;

public class LoginFacade {

    private AbstractDAOFactory daoFactory;
    private UserDAO userDAO;

    /**
     * Instantiates a Facade which gives the UI access to the business logic.
     */
    public LoginFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.userDAO = this.daoFactory.getUserDAO();
    }

    /**
     * Attempts to login with the given credentials.
     * @param email the user's email
     * @param password the user's password
     * @return the corresponding user instance if the credentials are correct; otherwise throws a RuntimeException
     */
    public User login(String email, String password) {
        User user = this.userDAO.find(email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("Incorrect password.");
    }
}
