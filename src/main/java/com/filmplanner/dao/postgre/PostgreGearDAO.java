package com.filmplanner.dao.postgre;

import com.filmplanner.dao.GearDAO;
import com.filmplanner.models.Gear;
import com.filmplanner.models.Shooting;

import java.sql.*;
import java.util.ArrayList;

public class PostgreGearDAO implements GearDAO {
    private Connection connection;

    public PostgreGearDAO(Connection connection) {

        this.connection = connection;
    }

    @Override
    public boolean createGear(Gear newGear) {
        try {
            String sql = "INSERT INTO gear (serialnumber, model, category) VALUES (?,?, ?);";
            PreparedStatement smt = this.connection.prepareStatement(sql);
            smt.setString(1, newGear.getSerialNumber());
            smt.setString(2, newGear.getModel());
            smt.setString(3, newGear.getCategory());
            smt.executeUpdate();
            System.out.println("Operation done successfully");
            smt.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }



    @Override
    public Gear findGearById(String id) {
        try {
            String sql = "SELECT * FROM public.gear WHERE serialnumber = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1,id);

            ResultSet res=preparedStatement.executeQuery();
            res.next();
            Gear gear=new Gear(res.getString("SERIALNBUMBER"),res.getString("MODEL"),res.getString("CATEGORY")  );
            res.close();
            preparedStatement.close();
            return gear;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<Gear> findManyGearByShooting(Shooting shooting) {
        try {
            String sql = "SELECT * FROM public.gear WHERE serialnumber = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(shooting));

            ResultSet res=preparedStatement.executeQuery();
            ArrayList<Gear> gearList = new ArrayList<Gear>();

            while (res.next()) {
                gearList.add(new Gear(res.getString("SERIALNBUMBER"), res.getString("MODEL"), res.getString("CATEGORY")));

            }
            res.close();
            preparedStatement.close();
            return gearList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<Gear> findAllGear() {
        try {
            String sql = "SELECT * FROM public.gear ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet res=preparedStatement.executeQuery();
            ArrayList<Gear> gearList = new ArrayList<Gear>();

            while (res.next()) {
                gearList.add(new Gear(res.getString("SERIALNUMBER"), res.getString("MODEL"), res.getString("CATEGORY")));
            }
            res.close();
            preparedStatement.close();
            return gearList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteGear(String id) {
        try {
            String sql = "DELETE FROM public.gear WHERE serialnumber = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateGear(String id, Gear gear) {
        try {
            String sql = "UPDATE public.gear SET serialnumber = ?, model= ? , category= ? WHERE serialnumber = ?;";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, gear.getSerialNumber());
            preparedStatement.setString(2, gear.getModel());
            preparedStatement.setString(3, gear.getCategory());
            preparedStatement.setString(4, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
