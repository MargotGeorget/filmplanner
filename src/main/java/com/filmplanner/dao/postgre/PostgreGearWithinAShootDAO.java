package com.filmplanner.dao.postgre;

import com.filmplanner.dao.GearWithinAShootDAO;
import com.filmplanner.models.GearWithinAShoot;

import java.sql.*;

public class PostgreGearWithinAShootDAO implements GearWithinAShootDAO {
    private Connection connection;

    public PostgreGearWithinAShootDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public long create(GearWithinAShoot newInstance) {
        String sql = "INSERT INTO gear_within_a_shoot (gear, shoot) VaLUES(?,?)";
        long id = -1;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setLong(1, newInstance.getGearId());
            stmt.setLong(2, newInstance.getShootId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating gearWithinAProject failed, no rows affected.");
            }

            System.out.println("Operation done successfully");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public GearWithinAShoot[] getAllGearsWithinAShoot(long idShoot) {
        return new GearWithinAShoot[0];
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
