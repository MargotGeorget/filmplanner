package com.filmplanner.dao.mock;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.Client;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;

import java.util.HashMap;

public class MockProjectDAO implements ProjectDAO {

    private HashMap<Long, Project> projects;
    private static Long counterId = 2L;

    public MockProjectDAO() {
        this.projects = new HashMap<>();
        Client client1 = null;
        Client client2 = null;
        try {
            client1 = new Client(1, "Sweep", "App écologique", "Yannick", "yannick@sweep.fr", "1234567890");
            client2 = new Client(2, "AnimoConcept", "Accessoires pour animaux", "Bastien", "bastien@animo.fr", "0987654321");
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        this.projects.put(1L, new Project(1L, "Teaser Sweep", "Mise en avant de Sweep", client1));
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
        return new Project[0];
    }

    @Override
    public Project[] findAll() {
        return new Project[0];
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, Project project) {

    }
}
