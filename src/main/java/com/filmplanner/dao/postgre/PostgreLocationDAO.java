package com.filmplanner.dao.postgre;

import com.filmplanner.dao.LocationDAO;
import com.filmplanner.models.Location;

import java.sql.Connection;

public class PostgreLocationDAO implements LocationDAO {
    private Connection connection;

    PostgreLocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long create(Location newLocation) {
        return 0;
    }
}
