package com.filmplanner.dao;

import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.*;
import java.util.HashMap;
import java.util.List;

public interface ShootDAO {

    /**
     * Create a shoot in the datasource
     * @param newShoot
     * @return long : new shoot's id
     */
    long create(Shoot newShoot);

    /**
     * Returns the shoot with the id entered in the datasource
     * @param id
     * @return Shoot : the shoot find in the datasource or null if no shoot has been found
     */
    Shoot getOneById(long id);

    /**
     * Modifies a shoot in the datasource
     * @param shoot : the modified information of the shoot
     * @return boolean : true if the shoot was in the datasource and it has been updated
     *                        or false if the shoot could not be found or could not be updated
     */
    boolean update(Shoot shoot) throws InvalidInputException;

    /**
     * Return all the shoots saved in the datasource for a given project
     * @param idProject
     * @return List of Shoot
     */
    List<Shoot> findAllShootInProject(long idProject);

    /**
     * Delete a shoot in the datasource
     * @param idShoot long : the id of the shoot to be deleted
     * @return boolean : true if the shoot was in the datasource and it has been deleted
     *                  or false if the shoot could not be found or could not be removed
     */
    boolean delete(long idShoot);

    /*
    -------------------- Management gear within a shoot --------------------
     */

    /**
     * Create a gearWithinAShoot in the datasource
     * @param newInstance
     * @return long : new gearWithinAShoot's id
     */
    boolean createGearWithinAShoot(GearWithinAShoot newInstance) throws InvalidInputException;

    /**
     * Returns the gearWithinAShoot with the idShoot and the idGear corresponding to his information in the datasource
     * @param idShoot long
     * @param idGear string
     * @return GearWithinAShoot: the gearWithinAShoot find in the datasource or null if no gearWithinAShoot has been found
     */
    GearWithinAShoot getOneGearsWithinAShoot(long idShoot, String idGear);

    /**
     * Return all the gears registered in a shoot
     * @param idShoot long
     * @return List of Gear
     */
    List<Gear> getAllGearsWithinAShoot(long idShoot);

    /**
     * Return all the shoot using a particular gear
     * @param idGear long
     * @return List of Shoot
     */
    List<Shoot> getAllShootUsingAGear(String idGear);

    /**
     * Delete a gearWithinAShoot in the datasource
     * @param id long : the id of the gearWithinAShoot to be deleted
     * @return boolean : true if the gearWithinAShoot was in the datasource and it has been deleted
     *                  or false if the gearWithinAShoot could not be found or could not be removed
     */
    boolean deleteGearWithinAShoot(long id);

    /**
     * Delete all gearWithinAShoot corresponding to a particular shoot
     * @param id long : if of the shoot
     * @return boolean : true if all gearWithinAShoot corresponding to the shoot has been deleted; false otherwise
     */
    boolean deleteAllGearWithinAShoot(long id);

    /*
    -------------------- Management user within a shoot --------------------
     */

    /**
     * Return all members in a shoot registered in the datasource
     * @param shoot
     * @return
     */
    HashMap<User,Role> allUserInAShoot(Shoot shoot);

    /**
     * Crearte a member_within_a_shoot in the datasource
     * @param shoot
     * @param user
     * @param role
     * @return boolean : true if the member_within_a_shoot has been created; false otherwise
     */
    boolean addUserInAShoot(Shoot shoot,User user, Role role);

    /**
     * Delete a member_within_a_shoot with the idShoot and the idGear corresponding to his information in the datasource
     * @param shoot
     * @param user
     * @return boolean : true if the member_within_a_shoot was in the datasource and it has been deleted
     *                  or false if the member_within_a_shoot could not be found or could not be removed
     */
    boolean deleteUserInAShoot(Shoot shoot, User user);

    //TODO: delete all user in a shoot
}
