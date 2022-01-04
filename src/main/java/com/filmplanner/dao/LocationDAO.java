package com.filmplanner.dao;

import com.filmplanner.models.Location;

public interface LocationDAO {

    /**
     * Create a location in the datasource
     * @param newLocation
     * @return long : new location's id
     */
    long create(Location newLocation);

    /**
     * Returns the location with the id entered in the datasource
     * @param id
     * @return Location : the location find in the datasource or null if no location has been found
     */
    Location findById(long id);

    /**
     * Modifies a location in the datasource
     * @param location : the modified information of the location
     * @return boolean : true if the location was in the datasource and it has been updated
     *                        or false if the location could not be found or could not be updated
     */
    boolean update(Location location);

    /**
     * Delete a location in the datasource
     * @param id long : the id of the location to be deleted
     * @return boolean : true if the location was in the datasource and it has been deleted
     *                  or false if the location could not be found or could not be removed
     */
    boolean delete(long id);
}
