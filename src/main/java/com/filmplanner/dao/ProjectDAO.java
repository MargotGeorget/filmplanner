package com.filmplanner.dao;

import com.filmplanner.models.Project;
import com.filmplanner.models.User;

public interface ProjectDAO {

    Project create(Project project);

    Project findById(Long id);

    Project[] findManyByManager(User manager);

    Project[] findAll();

    void deleteById(Long id);

    void updateById(Long id, Project project);
}
