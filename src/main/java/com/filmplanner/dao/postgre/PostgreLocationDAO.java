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
        String sql = "INSERT INTO location (street_number, street, city, zipCode) VaLUES(?,?,?,?)";
        long id = -1;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, location.getStreetNumber());
            stmt.setString(2, location.getStreet());
            stmt.setString(3, location.getCity());
            stmt.setString(4, location.getZipCode());

            int affectedRows = stmt.executeUpdate();
            System.out.println("ok");

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong("location_id");
                    location.setId(id);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            System.out.println("Operation done successfully");
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

    @Override
    public boolean update(Location location) {
        String sql = "UPDATE location SET street_number=?, street=?, city=?, zipcode=? WHERE location_id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setInt(1, location.getStreetNumber());
            stmt.setString(2, location.getStreet());
            stmt.setString(3, location.getCity());
            stmt.setString(4, location.getZipCode());
            stmt.setLong(5, location.getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update location failed, no rows affected.");
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("Operation done successfully");
        return true;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM location WHERE location_id=" + id + ";";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private Location getBasicLocationFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("location_id");
        int streetNumber = rs.getInt("street_number");
        String street = rs.getString("street");
        String city = rs.getString("city");
        String zipCode = rs.getString("zipcode");
        return new Location(id, streetNumber, street, city, zipCode);
    }

}
