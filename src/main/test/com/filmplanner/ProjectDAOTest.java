package com.filmplanner;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.mock.MockDAOFactory;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import org.junit.jupiter.api.Test;
import utils.ValidationUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.HashMap;

public class ProjectDAOTest {

    @Test
    public void projectCreationTest() {
        ProjectDAO projectDAO = MockDAOFactory.getInstance().getProjectDAO();
        Client moana = null;
        try {
            moana = new Client(3L, "Moana yachting", "Yachting agency", "Bernard", "bernard@moana.com", "1212121212");
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        Project project = new Project("Motor Yacht E-Motion", "Video to promote the yacth as a charter yacth", moana, new HashMap<>());
        Project createdProject = projectDAO.create(project);
        assertNotNull(createdProject.getId());
    }
}
