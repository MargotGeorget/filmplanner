package com.filmplanner.dao.postgre;

import com.filmplanner.dao.GearDAO;
import com.filmplanner.dao.GearWithinAShootDAO;
import com.filmplanner.dao.ShootDAO;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreGearWithinAShootDAO implements GearWithinAShootDAO {
    private Connection connection;
    private GearDAO gearDAO;
    private ShootDAO shootDAO;

    public PostgreGearWithinAShootDAO(Connection connection) {
        this.connection = connection;
        this.gearDAO = PostgreDAOFactory.getInstance().getGearDAO();
        this.shootDAO = PostgreDAOFactory.getInstance().getShootDAO();;
    }


    @Override
    public boolean create(GearWithinAShoot newInstance) throws InvalidInputException {
        if(isPresent(newInstance.getShootId(), newInstance.getGearId())){
            throw new InvalidInputException("This gear is already present in this shoot!");
        }
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
    public GearWithinAShoot getOne(long idShoot, String idGear) {
        GearWithinAShoot gearWithinAShoot = null;
        String sql = "SELECT * FROM  gear_within_a_shoot WHERE shoot = ? AND gear = ?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setLong(1, idShoot);
            stmt.setString(2, idGear);

            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                while (rs.next()) {
                    gearWithinAShoot = this.getBasicGearWithinAShootFromResultSet(rs);
                }
            }
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gearWithinAShoot;
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
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gears;
    }

    @Override
    public List<Shoot> getAllShootUsingAGear(String idGear) {
        List<Shoot> shoots = new ArrayList<>();
        String sql = "SELECT * FROM  gear_within_a_shoot WHERE gear = "+ idGear;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                while (rs.next()) {
                    shoots.add(this.shootDAO.getOneById(rs.getLong("shoot")));
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

    private GearWithinAShoot getBasicGearWithinAShootFromResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("gear_within_a_shoot_id");
        Long shoot = rs.getLong("shoot");
        String gear = rs.getString("gear");
        return new GearWithinAShoot(id, shoot, gear);
    }

    private boolean isPresent(long idShoot, String idGear) {
        List<Gear> gears = this.getAllGearsWithinAShoot(idShoot);
        boolean isPresent = false;
        int i = 0;
        while(i<gears.size() && !isPresent){
            if(gears.get(i).getSerialNumber().equals(idGear)){
                isPresent = true;
            }
            i++;
        }
        return isPresent;
    }

    private boolean isUsedAtThisDate(long idShoot, String idGear){
        Shoot shoot = this.shootDAO.getOneById(idShoot);
        List<Shoot> shoots = this.getAllShootUsingAGear(idGear);
        boolean isUsedAtThisDate = false;
        int i = 0;
        while(i<shoots.size() && !isUsedAtThisDate){
            if(shoots.get(i).getDate().equals(shoot.getDate())){
                isUsedAtThisDate = true;
            }
            i++;
        }
        return isUsedAtThisDate;
    }
}
