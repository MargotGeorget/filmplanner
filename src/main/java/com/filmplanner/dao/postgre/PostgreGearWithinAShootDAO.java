package com.filmplanner.dao.postgre;

import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.GearWithinAShootDAO;
import com.filmplanner.models.Gear;
import com.filmplanner.models.GearWithinAShoot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreGearWithinAShootDAO implements GearWithinAShootDAO {
    private Connection connection;
    private GearDAO gearDAO;

    public PostgreGearWithinAShootDAO(Connection connection) {
        this.connection = connection;
        this.gearDAO = PostgreDAOFactory.getInstance().getGearDAO();
    }


    @Override
    public boolean create(GearWithinAShoot newInstance) {
        String sql = "INSERT INTO gear_within_a_shoot (gear, shoot) VaLUES(?,?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, newInstance.getGearId());
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
        return true;
    }

    @Override
    public List<Gear> getAllGearsWithinAShoot(long idShoot) {
        List<Gear> gears = new ArrayList<>();
        String sql = "SELECT * FROM  gear_within_a_shoot WHERE shoot = "+ idShoot;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                while (rs.next()) {
                    gears.add(this.gearDAO.findGearById(rs.getString("gear")));
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
        return gears;
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM gear_within_a_shoot WHERE gear_within_a_shoot_id=" + id + ";";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean deleteAllGearWithinAShoot(long idShoot) {
        String sql = "DELETE FROM gear_within_a_shoot WHERE shoot=" + idShoot + ";";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
