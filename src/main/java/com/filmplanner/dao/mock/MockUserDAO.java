package com.filmplanner.dao.mock;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

public class MockUserDAO implements UserDAO {

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    MockUserDAO() {}

    @Override
    public User find(String email) {
        if (email.equalsIgnoreCase("margot.georget@gmail.com")) {
            return new User("Margot Georget", "margot.georget@gmail.com", "ruby");
        }
        return new User("Mock User", "mock-user@gmail.com", "mock-password");
    }

    @Override
    public String getPassword(String email) {
        if (email.equalsIgnoreCase("margot.georget@gmail.com")) {
            return "ruby";
        }
        return "mock-password";
    }
}
