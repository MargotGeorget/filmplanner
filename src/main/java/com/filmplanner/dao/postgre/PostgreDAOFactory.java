package com.filmplanner.dao.postgre;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.UserDAO;
import io.github.cdimascio.dotenv.Dotenv;

public class PostgreDAOFactory extends AbstractDAOFactory {

    private static PostgreDAOFactory instance;
    private UserDAO userDAO;
    private final String url;
    private final String password;
    private final String user;

    private PostgreDAOFactory() {
        Dotenv dotenv = Dotenv.load();
        this.user = dotenv.get("DB_USER");
        this.url = dotenv.get("DB_URL");
        this.password = dotenv.get("DB_PASSWORD");
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
            this.userDAO = new PostgreUserDAO(this.url, this.user, this.password);
        }
        return this.userDAO;
    }
}
