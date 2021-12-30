package com.filmplanner.facades;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.exceptions.InvalidCredentialsException;
import com.filmplanner.exceptions.UserNotFoundException;
import com.filmplanner.models.User;

import java.sql.SQLException;

public class LoginFacade {

    private static LoginFacade instance;
    private final AbstractDAOFactory daoFactory;
    private UserDAO userDAO;
    private User currentUser;

    /**
     * Instantiates a LoginFacade. This facades gives the UI access to the login business logic.
     */
    private LoginFacade() {
        this.daoFactory = PostgreDAOFactory.getInstance();
        this.userDAO = this.daoFactory.getUserDAO();
    }

    /**
     * Gets the single LoginFacade instance.
     *
     * @return the single LoginFacade instance.
     */
    public static LoginFacade getInstance() {
        if (instance == null) {
            instance = new LoginFacade();
        }
        return instance;
    }

    /**
     * Gets the current logged user of the application.
     *
     * @return the current user if he is logged in; null otherwise
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Attempts to login with the given credentials.
     *
     * @param email    the user's email
     * @param password the user's password
     * @return the corresponding user instance if the credentials are correct; otherwise throws a RuntimeException
     * @throws InvalidCredentialsException if the password doesn't match the email
     */
    public User login(String email, String password) throws InvalidCredentialsException, UserNotFoundException {
        this.currentUser = this.userDAO.findByEmail(email);
        if (this.currentUser == null) {
            throw new UserNotFoundException("The email " + email + " is not registered");
        }
        if (this.currentUser.getPassword().equals(password)) {
            return this.currentUser;
        } else {
            throw new InvalidCredentialsException("Incorrect password.");
        }
    }
}
