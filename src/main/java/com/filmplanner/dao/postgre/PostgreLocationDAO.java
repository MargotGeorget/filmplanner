package com.filmplanner.dao.postgre;

import com.filmplanner.dao.LocationDAO;
import com.filmplanner.models.Location;

import java.sql.*;

public class PostgreLocationDAO implements LocationDAO {
    private Connection connection;

    PostgreLocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long create(Location location){
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
    public Location findById(long id) {
        Location loc = null;
        String sql = "SELECT * FROM location WHERE location_id = " + id;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                //get the ID back
                if (rs.next()) {
                    loc = getBasicLocationFromResultSet(rs);
                }
            }
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loc;
    }

    private Location getBasicLocationFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("location_id");
        int streetNumber = rs.getInt("number");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String zipCode = rs.getString("zipcode");
        return new Location(id, streetNumber, street, city, zipCode);
    }

}
