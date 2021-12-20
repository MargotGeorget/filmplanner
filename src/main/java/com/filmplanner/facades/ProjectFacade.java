package com.filmplanner.facades;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;

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

    public Project[] findManyByManager(User manager) {
        return this.postgreProjectDAO.findManyByManager(manager);
    }

    public Project findById(Long id) {
        return this.postgreProjectDAO.findById(id);
    }

    public void deleteById(Long id) {
        this.postgreProjectDAO.deleteById(id);
    }

    public void updateById(Long id, Project project) {
        this.postgreProjectDAO.updateById(id, project);
    }

    public Project createProject(Project project) {
        return this.postgreProjectDAO.create(project);
    }
}
