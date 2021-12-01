package com.filmplanner.dao.postgre;

import com.filmplanner.models.User;
import com.filmplanner.dao.UserDAO;

public class PostgreUserDAO implements UserDAO {

    @Override
    public User find(String email) {
        return null;
    }

    @Override
    public String getPassword(String email) {
        return null;
    }
}
