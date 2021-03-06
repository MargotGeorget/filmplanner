package com.filmplanner.dao.postgre;

import com.filmplanner.dao.*;
import com.filmplanner.exceptions.InvalidInputException;
import com.filmplanner.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostgreShootDAO implements ShootDAO {

    private Connection connection;
    private LocationDAO locationDAO;
    private GearDAO gearDAO;

    public PostgreShootDAO(Connection connection) {
        this.connection = connection;
        this.locationDAO = PostgreDAOFactory.getInstance().getLocationDAO();
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
            stmt.setLong(5,shoot.getProjectId());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating shoot failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getLong("shoot_id");
                    shoot.setIdShoot(id);
                }
                else {
                    throw new SQLException("Creating shoot failed, no ID obtained.");
                }
            }

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
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoot;
    }

    @Override
    public boolean update(Shoot shoot) throws InvalidInputException {
        List<Gear> gears = this.getAllGearsWithinAShoot(shoot.getIdShoot());
        for(Gear gear: gears) {
            if (isUsedAtThisDate(shoot.getDate(), gear.getSerialNumber())) {
                throw new InvalidInputException("This gear is already used in a shoot at the same date!");
            }
        }
        String sql = "UPDATE shoot SET name=?, description=?, date=?, project=?, location=? WHERE shoot_id=?";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

            stmt.setString(1, shoot.getName());
            stmt.setString(2, shoot.getDescription());
            stmt.setString(3, shoot.getDate());
            stmt.setLong(4, shoot.getProjectId());
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
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoots;
    }

    @Override
    public boolean delete(long shootId) {
        Shoot shoot = this.getOneById(shootId);
        //Supprimer tout les gear_within_a_shoot qui le  concerne
        this.deleteAllGearWithinAShoot(shootId);
        this.deleteAllUsersWithinAShoot(shootId);

        String sql = "DELETE FROM shoot WHERE shoot_id=" + shootId + ";";

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
            Long projectId = rs.getLong("project");
            List<Gear> gears = this.getAllGearsWithinAShoot(id);

            //TODO : add verif
            shoot = new Shoot(id, name, description, date, location, gears, projectId);
            shoot.setMembers(this.allUserInAShoot(shoot));
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
        Shoot shoot = this.getOneById(newInstance.getShootId());
        if(isUsedAtThisDate(shoot.getDate(), newInstance.getGearId())){
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
        while (i < gears.size() && !isPresent) {
            if (gears.get(i).getSerialNumber().equals(idGear)) {
                isPresent = true;
            }
            i++;
        }
        return isPresent;
    }

    private boolean isUsedAtThisDate(String date, String idGear) {
        List<Shoot> shoots = this.getAllShootUsingAGear(idGear);
        boolean isUsedAtThisDate = false;
        int i = 0;
        while (i < shoots.size() && !isUsedAtThisDate) {
            if (shoots.get(i).getDate().equals(date)) {
                isUsedAtThisDate = true;
            }
            i++;
        }
        return isUsedAtThisDate;
    }

    /*
    -------------------- Management member within a shoot --------------------
     */
    @Override
    public HashMap<User, Role> allUserInAShoot(Shoot shoot) {
        HashMap<User, Role> member = new HashMap<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT user_id,fp_user.NAME,EMAIL,PASSWORD,PHONENUMBER,isadmin,role FROM fp_user JOIN member_within_a_shoot ON user_id=member where shoot = ?;");
            stmt.setLong(1, shoot.getIdShoot());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getLong("user_id"), rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("PHONENUMBER"), rs.getBoolean("isadmin"));
                Role role = new Role(rs.getString("role"));
                member.put(user, role);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return member;

    }

    @Override
    public boolean addUserInAShoot(Shoot shoot, User user, Role role) {

        try {
            PreparedStatement stmt = this.connection.prepareStatement("insert into member_within_a_shoot (member, role, shoot) VALUES (?,?,?);  ");
            stmt.setLong(1, user.getId());
            stmt.setString(2, role.getName());
            stmt.setLong(3, shoot.getIdShoot());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public boolean deleteUserInAShoot(Shoot shoot, User user) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM member_within_a_shoot where member= ? AND shoot = ?;");

            stmt.setLong(1, user.getId());
            stmt.setLong(2,shoot.getIdShoot());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAllUsersWithinAShoot(long idShoot) {
        String sql = "DELETE FROM member_within_a_shoot WHERE shoot=" + idShoot + ";";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


}
