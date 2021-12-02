package com.filmplanner.dao.postgre;

import com.filmplanner.models.User;
import com.filmplanner.dao.UserDAO;

import java.sql.*;

public class PostgreUserDAO implements UserDAO {

    private String url;
    private String user;
    private String password;
    private Connection c = null;
    private Statement stmt = null;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    PostgreUserDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // TODO move method into a class accessible for all DAOs
    private Connection openConnection() {
        try {
            this.c = DriverManager.getConnection(this.url, this.user, this.password);
            this.c.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
            //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
        return this.c;
    }

    // TODO move method into a class accessible for all DAOs
    private void closeConnection() {
        try {
            this.stmt.close();
            this.c.close();
            System.out.println("Close database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public User find(String email) {
        User newUser = null;
        this.openConnection();
        try {
            this.stmt = this.c.createStatement();
            ResultSet rs = this.stmt.executeQuery( "SELECT * FROM public.\"USER\" WHERE EMAIL='"+email+"';" );
            rs.next();
            newUser = new User(rs.getString("NAME"),rs.getString("EMAIL"),rs.getString("PASSWORD"));
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeConnection();
        System.out.println("Operation done successfully");
        return newUser;
    }

    @Override
    public String getPassword(String email) {
        String password = null;
        this.openConnection();
        try {
            this.stmt = this.c.createStatement();
            ResultSet rs = this.stmt.executeQuery( "SELECT * FROM public.\"USER\" WHERE EMAIL='"+email+"';" );
            rs.next();
            password = rs.getString("PASSWORD");
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeConnection();
        System.out.println("Operation done successfully");
        return password;

    }

    // TODO add the method into the UserDAO abstract class
    // TODO let the method take a User as a parameter so it can really insert a user in the DB
    public void insert() {
        this.openConnection();
        try {
            this.stmt = this.c.createStatement();
            ResultSet rs = this.stmt.executeQuery("INSERT INTO public.\"USER\" (email, password, name, phonenumber) VALUES ('aaaaas', 'sdgg', 'gdgsg', null);");
            rs.next();//
            //insert into public."USER" (email, password, name, phonenumber)  VALUES ("nathan@se.com","ruby","nathan","06666666");
            rs.close();//

        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.closeConnection();
        System.out.println("Operation done successfully");
    }
}
