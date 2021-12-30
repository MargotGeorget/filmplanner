package com.filmplanner.dao.postgre;

import com.filmplanner.dao.*;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreShootDAO implements ShootDAO {

    private Connection connection;
    private LocationDAO locationDAO;
    private ProjectDAO projectDAO;
    private GearDAO gearDAO;

    public PostgreShootDAO(Connection connection) {
        this.connection = connection;
        this.locationDAO = PostgreDAOFactory.getInstance().getLocationDAO();
        this.projectDAO = PostgreDAOFactory.getInstance().getProjectDAO();
        this.gearDAO = PostgreDAOFactory.getInstance().getGearDAO();
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

    @Override
    public boolean delete(long idShoot) {
        Shoot shoot = this.getOneById(idShoot);
        //Supprimer tout les gear_within_a_shoot qui le  concerne
        this.deleteAllGearWithinAShoot(idShoot);
        //TODO: faire pareil avec les users dans un shoot

        String sql = "DELETE FROM shoot WHERE shoot_id=" + idShoot + ";";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Supprimer la location correspondant au shoot

        this.locationDAO.delete(shoot.getLocation().getId());
        return true;
    }

    private Shoot getBasicShootFromResultSet(ResultSet rs) throws SQLException {
        Shoot shoot = null;
        try {
            Long id = rs.getLong("shoot_id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String date = rs.getString("date");
            Location location = this.locationDAO.findById(rs.getLong("location"));
            Project project = this.projectDAO.findById(rs.getLong("project"));
            List<Gear> gears = this.getAllGearsWithinAShoot(id);
            //TODO: add gears and members
            //TODO : add verif
            shoot = new Shoot(id, name, description, date, location, gears, project);
        } catch (InvalidInputException e){
            System.out.println(e.getMessage());
        }
        return shoot;
    }

    /*
    -------------------- Management gear within a shoot --------------------
     */
    @Override
    public boolean createGearWithinAShoot(GearWithinAShoot newInstance) throws InvalidInputException {
        if(isPresent(newInstance.getShootId(), newInstance.getGearId())){
            throw new InvalidInputException("This gear is already present in this shoot!");
        }
        if(isUsedAtThisDate(newInstance.getShootId(), newInstance.getGearId())){
            throw new InvalidInputException("This gear is already used in a shoot at the same date!");
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
    public GearWithinAShoot getOneGearsWithinAShoot(long idShoot, String idGear) {
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
        String sql = "SELECT * FROM gear_within_a_shoot WHERE gear =? ";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, idGear);
            ResultSet rs = stmt.executeQuery();

            //check the affected rows
            if (rs != null) {
                while (rs.next()) {
                    shoots.add(this.getOneById(rs.getLong("shoot")));
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
    public boolean deleteGearWithinAShoot(long id) {
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
        Shoot shoot = this.getOneById(idShoot);
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
