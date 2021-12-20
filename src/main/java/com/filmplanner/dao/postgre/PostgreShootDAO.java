package com.filmplanner.dao.postgre;

import com.filmplanner.dao.ShootDAO;

import java.sql.Connection;

public class PostgreShootDAO implements ShootDAO {

    private Connection connection;

    public PostgreShootDAO(Connection connection) {
        this.connection = connection;
    }
}
