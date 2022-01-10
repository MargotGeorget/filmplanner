package com.filmplanner.dao.mock;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockProjectDAO implements ProjectDAO {

    private HashMap<Long, Project> projects;
    private static Long counterId = 2L;
    private UserDAO userDAO = MockDAOFactory.getInstance().getUserDAO();

    public MockProjectDAO() {
        this.projects = new HashMap<>();
        Client client1 = null;
        Client client2 = null;
        User user1 = this.userDAO.findByEmail("kevin@gmail.com");
        try {
            client1 = new Client(1, "Sweep", "App écologique", "Yannick", "yannick@sweep.fr", "1234567890");
            client2 = new Client(2, "AnimoConcept", "Accessoires pour animaux", "Bastien", "bastien@animo.fr", "0987654321");
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        Project project1 = new Project(1L, "Teaser Sweep", "Mise en avant de Sweep", client1);
        project1.addManager(user1);
        this.projects.put(1L, project1);
        this.projects.put(2L, new Project(2L, "Gamme propreté", "Mise en avant gamme propreté animoconcept", client2));
    }


    @Override
    public Project create(Project project) {
        Project createdProject = new Project(counterId++, project);
        this.projects.put(createdProject.getId(), createdProject);
        return createdProject;
    }

    @Override
    public Project findById(Long id) {
        return this.projects.get(id);
    }

    @Override
    public Project[] findManyByManager(User manager) {
        List<Project> projects = new ArrayList<>();
        for (Project project : this.projects.values()) {
            for (User projectManager : project.getManagers()) {
                if (projectManager.getId() == manager.getId()) {
                    projects.add(project);
                }
            }
        }
        return projects.toArray(new Project[0]);
    }

    @Override
    public Project[] findAll() {
        return this.projects.values().toArray(new Project[0]);
    }

    @Override
    public boolean deleteById(Long id) {
        return this.projects.remove(id) != null;
    }

    @Override
    public void updateById(Long id, Project project) {
        this.projects.put(id, project);
    }
}
