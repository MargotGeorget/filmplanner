package com.filmplanner;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.dao.mock.MockDAOFactory;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;



import java.util.HashMap;

public class ProjectDAOTest {

    private MockDAOFactory mockDAOFactory = MockDAOFactory.getInstance();
    private ProjectDAO projectDAO = mockDAOFactory.getProjectDAO();
    private UserDAO userDAO = mockDAOFactory.getUserDAO();

    // CRUD Project usecase
    @Test
    public void projectCreationTest() {
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

    // Managers within a project test
    @Test
    public void findProjectsByManagerTest() {
        User user = this.userDAO.findByEmail("kevin@gmail.com");
        assertEquals(1L, this.projectDAO.findManyByManager(user).length);
    }
}
