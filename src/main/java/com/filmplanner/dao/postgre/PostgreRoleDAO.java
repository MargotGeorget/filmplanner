package com.filmplanner.dao.postgre;

import com.filmplanner.dao.RoleDAO;
import com.filmplanner.models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PostgreRoleDAO implements RoleDAO {
    private final Connection connection;

    public PostgreRoleDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * @param newRole to create in the database
     * @return true if it's done else false
     */
    @Override
    public boolean createRole(Role newRole) {
        try {
            String sql = "INSERT INTO role VALUES (?);";
            PreparedStatement smt = this.connection.prepareStatement(sql);
            smt.setString(1, newRole.getName());
            smt.executeUpdate();
            smt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return ArrayList with all the role in the database
     */

    @Override
    public ArrayList<Role> findAllRole() {
        try {
            String sql = "SELECT * FROM public.role ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet res = preparedStatement.executeQuery();
            ArrayList<Role> roleList = new ArrayList<>();

            while (res.next()) {
                roleList.add(new Role(res.getString("name")));
            }
            res.close();
            preparedStatement.close();
            return roleList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param id to delete in the database
     * @return true if it's done else false
     */

    @Override
    public boolean deleteRole(String id) {
        try {
            String sql = "DELETE FROM role WHERE name = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
