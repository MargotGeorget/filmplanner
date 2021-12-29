package com.filmplanner.facades;

import com.filmplanner.dao.PaperworkDAO;
import com.filmplanner.dao.postgre.PostgreDAOFactory;
import com.filmplanner.models.Paperwork;
import com.filmplanner.models.Project;

public class PaperworkFacade {

    private static PaperworkFacade instance;
    private PaperworkDAO paperworkDAO;

    private PaperworkFacade() {
        this.paperworkDAO = PostgreDAOFactory.getInstance().getPaperworkDAO();
    }

    public static PaperworkFacade getInstance() {
        if (instance == null) {
            instance = new PaperworkFacade();
        }
        return instance;
    }

    public Paperwork create(Paperwork paperwork, Long projectId) {
        return this.paperworkDAO.create(paperwork, projectId);
    }

    public Paperwork[] findManyByProjectId(Project project) {
        return this.paperworkDAO.findManyByProject(project);
    }

    public void delete(Long id) {
        this.paperworkDAO.delete(id);
    }
}
