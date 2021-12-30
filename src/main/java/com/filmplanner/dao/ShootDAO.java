package com.filmplanner.dao;

import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import com.filmplanner.models.Gear;
import com.filmplanner.models.GearWithinAShoot;
import com.filmplanner.models.Shoot;

import java.util.List;

public interface ShootDAO {

    /**
     * Create a shoot in the datasource
     * @param newShoot
     * @return long : new shoot's id
     */
    long create(Shoot newShoot);

    /**
     *
     */
    Shoot getOneById(long id);

    boolean update(Shoot shoot);

    /**
     * Return all the shoot saved in the database for a given project
     * @param idProject
     * @return List<Shoot>
     */
    List<Shoot> findAllShootInProject(long idProject);

    boolean delete(long idShoot);


    /**
     * Create a gearWithinAShoot in the datasource
     * @param newInstance
     * @return long : new gearWithinAShoot's id
     */
    boolean createGearWithinAShoot(GearWithinAShoot newInstance) throws InvalidInputException;

    GearWithinAShoot getOneGearsWithinAShoot(long idShoot, String idGear);

    /**
     * Return all the gears registered in the shoot
     * @param idShoot
     * @return
     */
    List<Gear> getAllGearsWithinAShoot(long idShoot);

    List<Shoot> getAllShootUsingAGear(String idGear);

    /**
     * Delete a gearWithinAShoot in the datasource
     * @param id long : the id of the gearWithinAShoot to be deleted
     * @return boolean : true if the gearWithinAShoot was in the datasource and it has been deleted
     *                  or false if the gearWithinAShoot could not be found or could not be removed
     */
    boolean deleteGearWithinAShoot(long id);

    boolean deleteAllGearWithinAShoot(long id);
}
