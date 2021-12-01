package com.filmplanner.dao.postgre;

import com.filmplanner.models.User;
import com.filmplanner.dao.UserDAO;

public class PostgreUserDAO implements UserDAO {

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    PostgreUserDAO() {}

    @Override
    public User find(String email) {
        return null;
    }

    @Override
    public String getPassword(String email) {
        return null;
    }
}
