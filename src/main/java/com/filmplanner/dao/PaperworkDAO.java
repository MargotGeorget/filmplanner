package com.filmplanner.dao;

import com.filmplanner.models.Paperwork;
import com.filmplanner.models.Project;

public interface PaperworkDAO {

    Paperwork create(Paperwork paperwork);

    Paperwork[] findManyByProjectId(Project project);

    void delete(Long id);
}
