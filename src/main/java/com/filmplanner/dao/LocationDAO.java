package com.filmplanner.dao;

import com.filmplanner.models.Location;

public interface LocationDAO {

    /**
     * Create a location in the datasource
     * @param newLocation
     * @return long : new location's id
     */
    long create(Location newLocation);

    Location findById(long id);
}
