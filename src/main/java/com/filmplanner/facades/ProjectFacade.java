package com.filmplanner.facades;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.dao.postgre.PostgreProjectDAO;
import com.filmplanner.models.Project;

public class ProjectFacade {

    private static ProjectFacade instance;
    private ProjectDAO postgreProjectDAO;

    private ProjectFacade() {
        this.postgreProjectDAO = PostgreDAOFactory.getInstance().getProjectDAO();
    }

    /**
     * Gets the single ProjectFacade instance.
     *
     * @return the single ProjectFacade instance
     */
    public static ProjectFacade getInstance() {
        if (instance == null) {
            instance = new ProjectFacade();
        }
        return instance;
    }

    public Project[] findAll() {
        return this.postgreProjectDAO.findAll();
    }
}
