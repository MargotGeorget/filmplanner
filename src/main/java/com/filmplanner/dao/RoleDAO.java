package com.filmplanner.dao;

import com.filmplanner.models.Gear;
import com.filmplanner.models.Role;

import java.util.ArrayList;

public interface RoleDAO {

    /**
     * Create a role in the datasource
     * @param newRole
     * @return boolean : true if the role has been created; false otherwise
     */
    boolean createRole(Role newRole);

    /**
     * Returns all the role saved in the datasource
     * @return List<Role>
     */
    ArrayList<Role> findAllRole();

    /**
     * Delete a role in the datasource
     * @param id string : the id of the role to be deleted
     * @return boolean : true if the role was in the datasource and it has been deleted
     *                  or false if the role could not be found or could not be removed
     */
    boolean deleteRole(String id);
}
