package com.filmplanner.dao;

import com.filmplanner.models.Client;
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
}
