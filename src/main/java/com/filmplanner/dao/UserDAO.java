package com.filmplanner.dao;


import com.filmplanner.models.User;

import java.util.List;

public interface UserDAO {
    User findByEmail(String email);

    int update(Long id, User updatedUser);

    List<User> findAll();

    User findById(Long id);

    /**
     * @param newUser
     * @return 1 if successful, 0 if not
     * creates user in the database
     */
    Long create(User newUser);

    int deleteById(Long id);
}
