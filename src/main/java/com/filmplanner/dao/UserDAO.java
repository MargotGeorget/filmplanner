package com.filmplanner.dao;


import com.filmplanner.models.User;

import java.util.List;

public interface UserDAO {
    User findByEmail(String email);

    long update(int id, User updatedUser);

    List<User> findAll();

    User findById(int id);

    /**
     * @param newUser
     * @return 1 if successful, 0 if not
     * creates user in the database
     */
    int create(User newUser);

    int deleteById(int id);
}
