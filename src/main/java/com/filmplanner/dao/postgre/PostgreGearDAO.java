package com.filmplanner.dao.postgre;

import com.filmplanner.dao.GearDAO;
import com.filmplanner.models.Gear;
import com.filmplanner.models.Shooting;

import java.sql.*;
import java.util.ArrayList;

public class PostgreGearDAO implements GearDAO {
    private Connection connection;
    private Statement stmt;

    public PostgreGearDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createGear(Gear newGear) {
        try {
            String sql = "INSERT INTO public.\"GEAR\" (serialnumber, model, category) VALUES (?, ?, ?);";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, newGear.getSerialNumber());
            preparedStatement.setString(2, newGear.getModel());
            preparedStatement.setString(3, newGear.getCategory());

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
    }



    @Override
    public Gear findGearById(int id) {
        try {
            String sql = "SELECT * FROM public.\"GEAR\" WHERE serialnumber = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));

            ResultSet res=preparedStatement.executeQuery();
            res.next();
            Gear gear=new Gear(res.getString("SERIALNBUMBER"),res.getString("MODEL"),res.getString("CATEGORY")  );
            res.close();
            return gear;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ArrayList<Gear> findManyGearByShooting(Shooting shooting) {
        try {
            String sql = "SELECT * FROM public.\"GEAR\" WHERE serialnumber = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(shooting));

            ResultSet res=preparedStatement.executeQuery();
            ArrayList<Gear> gearList = new ArrayList<Gear>();

            while (res.next()) {
                gearList.add(new Gear(res.getString("SERIALNBUMBER"), res.getString("MODEL"), res.getString("CATEGORY")));

            }
            res.close();
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
            return gearList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteGear(int id) {
        try {
            String sql = "DELET FROM public.\"gear\" WHERE serialnumber = ? ;";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(id));

            preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateGear(int id, Gear gear) {
        try {
            String sql = "UPDATE public.\"GEAR\" SET serialnumber = ?, model= ? , category= ? WHERE serialnumber = ?;";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, gear.getSerialNumber());
            preparedStatement.setString(2, gear.getModel());
            preparedStatement.setString(3, gear.getCategory());
            preparedStatement.setString(4, String.valueOf(id));

            preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
    }
}
