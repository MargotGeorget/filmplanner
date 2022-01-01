package com.filmplanner.dao.mock;

import com.filmplanner.dao.RoleDAO;
import com.filmplanner.models.Role;

import java.util.ArrayList;


public class MockRoleDAO implements RoleDAO {

    private final ArrayList<Role> role;

     MockRoleDAO(){
        this.role = new ArrayList<Role>();
         role.add(new Role("Cadreur"));
         role.add(new Role("Monteur"));
    }
    @Override
    public boolean createRole(Role newRole) {
        return this.role.add(newRole);

    }

    @Override
    public ArrayList<Role> findAllRole() {
        return role;
    }

    @Override
    public boolean deleteRole(String id) {
         boolean res=false;
        for (Role item: this.role) {
            if (item.getName().equals(id)){
                this.role.remove(item);
                res=true;
            }
        }
        return res;
    }
}
