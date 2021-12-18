package com.filmplanner.dao;


import com.filmplanner.models.User;

public interface UserDAO {

    User findByEmail(String email);

    void create(User user);
}
