package com.filmplanner.dao;

import com.filmplanner.models.Gear;
import com.filmplanner.models.GearWithinAShoot;
import java.util.List;

public interface GearWithinAShootDAO {

    /**
     * Create a gearWithinAShoot in the datasource
     * @param newInstance
     * @return long : new gearWithinAShoot's id
     */
    boolean create(GearWithinAShoot newInstance);

    GearWithinAShoot getOne(long idShoot, String idGear);

    /**
     * Return all the gears registered in the shoot
     * @param idShoot
     * @return
     */
    List<Gear> getAllGearsWithinAShoot(long idShoot);

    /**
     * Delete a gearWithinAShoot in the datasource
     * @param id long : the id of the gearWithinAShoot to be deleted
     * @return boolean : true if the gearWithinAShoot was in the datasource and it has been deleted
     *                  or false if the gearWithinAShoot could not be found or could not be removed
     */
    boolean delete(long id);

    boolean deleteAllGearWithinAShoot(long id);
}
