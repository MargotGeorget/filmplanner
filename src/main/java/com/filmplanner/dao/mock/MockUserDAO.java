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
    public int update(Long id, User updatedUser) {
        return -1;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public Long create(User newUser) {return new Long(0);}

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
