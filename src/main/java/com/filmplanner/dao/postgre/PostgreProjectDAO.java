package com.filmplanner.dao.postgre;

import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.models.Project;
import com.filmplanner.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO fill in methods
public class PostgreProjectDAO implements ProjectDAO {

    private Connection connection;

    public PostgreProjectDAO(Connection connection) {
        this.connection = connection;
    }


    /*
    Methods
     */

    /**
     * Creates a new Project in the database given an instance of a Project.
     *
     * @param project the Project which will be inserted inside the database
     */
    @Override
    public void create(Project project) {
        try {
            String query = "INSERT INTO project (name, description, client) VALUES (?, ?, ?)";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            // statement.setString(3, client.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets a Project by its id.
     *
     * @param id the Project's id
     * @return the Project corresponding to the given id or null if the id doesn't correspond to any Project.
     */
    @Override
    public Project findById(String id) {
        try {
            String query = "SELECT * FROM project WHERE \"projectId\"=" + id;
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            Project project = new Project(resultSet.getString("name"), resultSet.getString("description"));
            resultSet.close();
            return project;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public Project[] findManyByManager(User manager) {
        return null;
    }

    /**
     * Gets all Projects from the database.
     *
     * @return an array containing all Projects present in the database
     */
    @Override
    public Project[] findAll() {
        try {
            List<Project> projects = new ArrayList<>(); // ArrayList is faster for storing and accessing data
            String query = "SELECT * FROM project";
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                projects.add(new Project(resultSet.getString("name"), resultSet.getString("description")));
            }
            return projects.toArray(new Project[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a project from the database given its id.
     *
     * @param id the id of the Project to delete
     */
    @Override
    public void deleteById(String id) {
        try {
            String query = "DELETE FROM project WHERE \"projectId\"=" + id;
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates a project in the database given a Project instance.
     *
     * @param project the Project instance which will be updated
     */
    @Override
    public void updateById(Project project) {
        try {
            String query = "UPDATE project SET name =" + project.getName() + "  WHERE \"projectId\"=";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
