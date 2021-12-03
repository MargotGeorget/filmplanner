package com.filmplanner.dao.mock;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

import java.util.HashMap;

public class MockUserDAO implements UserDAO {

    private HashMap<String, User> users;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    MockUserDAO() {
        this.users = new HashMap<>();
        users.put("toto@gmail.com", new User("Toto", "toto@gmail.com", "toto"));
        users.put("margot-georget@gmail.com", new User("Georgette", "margot-georget@gmail.com", "ruby"));
    }

    @Override
    public User find(String email) {
        return this.users.get(email);
    }

    @Override
    public String getPassword(String email) {
        return this.users.get(email).getPassword();
    }
}
