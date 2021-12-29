package com.filmplanner.dao.postgre;

import com.filmplanner.dao.GearWithinAShootDAO;
import com.filmplanner.dao.LocationDAO;
import com.filmplanner.dao.ProjectDAO;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.models.Gear;
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
    private GearWithinAShootDAO gearWithinAShootDAO;

    public PostgreShootDAO(Connection connection) {
        this.connection = connection;
        this.locationDAO = PostgreDAOFactory.getInstance().getLocationDAO();
        this.projectDAO = PostgreDAOFactory.getInstance().getProjectDAO();
        this.gearWithinAShootDAO = PostgreDAOFactory.getInstance().getGearWithinAShootDAO();
    }

    @Override
    public long create(Shoot shoot) {
        //Creation de la localisation
        long idLoc = this.locationDAO.create(shoot.getLocation());
        String sql = "INSERT INTO shoot (name, description, date, location, project) VaLUES(?,?,?,?,?)";
        long id = -1;

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, shoot.getName());
            stmt.setString(2, shoot.getDescription());
            stmt.setString(3, shoot.getDate());
            stmt.setLong(4, idLoc);
            stmt.setLong(5,shoot.getProject().getId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong("shoot_id");
                    shoot.setIdShoot(id);
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
    public Shoot getOneById(long id) {
        Shoot shoot = null;
        String sql = "SELECT * FROM shoot WHERE shoot_id = " + id;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                while (rs.next()) {
                    shoot = this.getBasicShootFromResultSet(rs);
                }
            }
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoot;
    }

    @Override
    public boolean update(Shoot shoot) {
        String sql = "UPDATE shoot SET name=?, description=?, date=?, project=?, location=? WHERE shoot_id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, shoot.getName());
            stmt.setString(2, shoot.getDescription());
            stmt.setString(3, shoot.getDate());
            stmt.setLong(4, shoot.getProject().getId());
            stmt.setLong(5, shoot.getLocation().getId());
            stmt.setLong(6, shoot.getIdShoot());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Update shoot failed, no rows affected.");
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
    public List<Shoot> findAllShootInProject(long idProject) {
        List<Shoot> shoots = new ArrayList<>();
        String sql = "SELECT * FROM shoot WHERE project = " + idProject;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                while (rs.next()) {
                    shoots.add(this.getBasicShootFromResultSet(rs));
                }
            }
            //TODO: récupérer les gears et les members du projet
            //Faire appel au postgreDAO correspondant :
            // getAllMembersByShoot()
            // getAllGearsByShoot()
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
        List<Gear> gears = this.gearWithinAShootDAO.getAllGearsWithinAShoot(id);
        //TODO: add gears and members
                //TODO : add verif
        return new Shoot(id, name, description, date, location, gears, project);
    }


}
