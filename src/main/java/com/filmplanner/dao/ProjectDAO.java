package com.filmplanner.dao;

import com.filmplanner.models.Project;
import com.filmplanner.models.User;

public interface ProjectDAO {

    void create(Project project);

    Project findById(String id);

    Project[] findManyByManager(User manager);

    Project[] findAll();

    void deleteById(String id);

    void updateById(Project project);
}
