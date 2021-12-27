package com.filmplanner.dao.postgre;

import com.filmplanner.dao.ShootDAO;
import com.filmplanner.models.Location;
import com.filmplanner.models.Shoot;

import java.sql.*;
import java.util.List;

public class PostgreShootDAO implements ShootDAO {

    private Connection connection;

    public PostgreShootDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long create(Shoot shoot) {
        //Creation de la localisation
        long idLoc = createLocation(shoot.getLocation());
        String sql = "INSERT INTO shoot (name, description, start_date, location, project) VaLUES(?,?,?,?,?)";
        long id = -1;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, shoot.getName());
            stmt.setString(2, shoot.getDescription());
            stmt.setString(3, shoot.getDate());
            stmt.setLong(4, idLoc);
            stmt.setLong(5,shoot.getProject().getId());

            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                //get the ID back
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            }
            System.out.println("Operation done successfully");

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private long createLocation(Location location){
        String sql = "INSERT INTO location (sreetNumber, street, city, zipCode) VaLUES(?,?,?,?)";
        long id = -1;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, location.getStreetNumber());
            stmt.setString(2, location.getStreet());
            stmt.setString(3, location.getCity());
            stmt.setString(4, location.getZipCode());

            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                //get the ID back
                if (rs.next()) {
                    id = rs.getLong(1);
                }
            }
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Shoot> findAllShootInProject(long idProject) {
        return null;
    }


}
