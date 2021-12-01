package com.filmplanner.dao;


import com.filmplanner.models.User;

public interface UserDAO {
    User find(String email);
    String getPassword(String email);
}
