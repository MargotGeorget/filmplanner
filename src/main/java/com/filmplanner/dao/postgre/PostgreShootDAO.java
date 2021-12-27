package com.filmplanner.dao.postgre;

import com.filmplanner.dao.LocationDAO;
import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.models.Location;
import com.filmplanner.models.Project;
import com.filmplanner.models.Shoot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreShootDAO implements ShootDAO {

    private Connection connection;
    private LocationDAO locationDAO;
    private ProjectDAO projectDAO;

    public PostgreShootDAO(Connection connection) {
        this.connection = connection;
        this.locationDAO = PostgreDAOFactory.getInstance().getLocationDAO();
        this.projectDAO = PostgreDAOFactory.getInstance().getProjectDAO();
    }

    @Override
    public long create(Shoot shoot) {
        //Creation de la localisation
        long idLoc = this.locationDAO.create(shoot.getLocation());
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

    @Override
    public List<Shoot> findAllShootInProject(long idProject) {
        List<Shoot> shoots = new ArrayList<>();
        String sql = "SELECT * FROM shoot WHERE project = " + idProject;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                //get the ID back
                while (rs.next()) {
                    shoots.add(this.getBasicShootFromResultSet(rs));
                }
            }
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoots;
    }

    private Shoot getBasicShootFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("shoot_id");
        String name = rs.getString("name");
        String description = rs.getString("description");
        String date = rs.getString("date");
        Location location = this.locationDAO.findById(rs.getLong("location"));
        Project project = this.projectDAO.findById(rs.getLong("project"));
                //TODO : add verif
        return new Shoot(id, name, description, date, location, project);
    }


}
