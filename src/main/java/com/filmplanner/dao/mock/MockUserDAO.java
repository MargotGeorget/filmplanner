package com.filmplanner.dao.mock;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

import java.util.HashMap;
import java.util.List;

public class MockUserDAO implements UserDAO {

    private final HashMap<String, User> users;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    MockUserDAO() {
        this.users = new HashMap<>();
        users.put("toto@gmail.com", new User("Toto", "toto@gmail.com", "toto", "06888888"));
        users.put("margot-georget@gmail.com", new User("Georgette", "margot-georget@gmail.com", "ruby"));
    }

    /**
     * Finds a User based
     *
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email) {
        return this.users.get(email);
    }

    @Override
    public long update(int id, User updatedUser) {
        return -1;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public int create(User newUser) {
        return 0;
    }

    @Override
    public int deleteById(int id) {
        return 0;
    }
}
