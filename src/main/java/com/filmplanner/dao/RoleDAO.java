package com.filmplanner.dao;

import com.filmplanner.models.Gear;
import com.filmplanner.models.Role;

import java.util.ArrayList;

public interface RoleDAO {
    boolean createRole(Role newRole);
    ArrayList<Role> findAllRole();

    boolean deleteRole(String id);
}
