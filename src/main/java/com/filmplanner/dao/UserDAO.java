package com.filmplanner.dao;


import com.filmplanner.models.User;

import java.util.List;

public interface UserDAO {

    User findByEmail(String email);

    void create(User user);

    List<User> findAll();
}
