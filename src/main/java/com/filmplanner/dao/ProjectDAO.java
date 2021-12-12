package com.filmplanner.dao;

import com.filmplanner.models.Project;
import com.filmplanner.models.User;

public interface ProjectDAO {

    Project create(Project project);

    Project findById(int id);

    Project[] findManyByManager(User manager);

    Project[] findAll();

    void deleteById(int id);

    void updateById(int id, Project project);
}
