package com.filmplanner.dao;

import com.filmplanner.models.Gear;

import java.util.ArrayList;

public interface GearDAO {

    /**
     * Create a gear in the datasource
     * @param newGear
     * @return boolean : true if the gear has been created; false otherwise
     */
    boolean createGear(Gear newGear);

    /**
     * Returns the gear with the id entered in the datasource
     * @param id
     * @return Gear : the gear find in the datasource or null if no gear has been found
     */
    Gear findGearById(String id);

    /**
     * Returns all the gears saved in the datasource
     * @return List<Gear>
     */
    ArrayList<Gear> findAllGear();

    /**
     * Delete a gear in the datasource
     * @param id string : the id of the gear to be deleted
     * @return boolean : true if the gear was in the datasource and it has been deleted
     *                  or false if the gear could not be found or could not be removed
     */
    boolean deleteGear(String id);

    /**
     * Modifies a gear in the datasource
     * @param id string : the id of the client to be modified
     * @param gear : the modified information of the gear
     * @return boolean : true if the gear was in the datasource and it has been updated
     *                        or false if the gear could not be found or could not be updated
     */
    boolean updateGear(String id, Gear gear);


}
