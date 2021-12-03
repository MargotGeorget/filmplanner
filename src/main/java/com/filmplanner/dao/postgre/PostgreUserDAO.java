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
    private final PostgreConnection postgreConnection;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    PostgreUserDAO(String url, String user, String password) {
        this.postgreConnection = new PostgreConnection(url, user, password);
    }


    @Override
    public User find(String email) {
        User newUser = null;
        this.c = this.postgreConnection.openConnection();
        try {
            this.stmt = this.c.createStatement();
            ResultSet rs = this.stmt.executeQuery( "SELECT * FROM public.\"USER\" WHERE EMAIL='"+email+"';" );
            rs.next();
            newUser = new User(rs.getString("NAME"),rs.getString("EMAIL"),rs.getString("PASSWORD"), rs.getString("PHONENUMBER")); //TODO : Gérer l'erreur quand le login est pas bon
            rs.close();
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.postgreConnection.closeConnection();
        System.out.println("Operation done successfully");
        return newUser;

    }

    @Override
    public String getPassword(String email) {
        String password = null;
        this.c = this.postgreConnection.openConnection();
        try {
            this.stmt = this.c.createStatement();
            ResultSet rs = this.stmt.executeQuery( "SELECT * FROM public.\"USER\" WHERE EMAIL='"+email+"';" );
            rs.next();
            password = rs.getString("PASSWORD");
            rs.close();
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.postgreConnection.closeConnection();
        System.out.println("Operation done successfully");
        return password;

    }
    // TODO add the method into the UserDAO abstract class /IMPOSSIBLE
    // TODO let the method take a User as a parameter so it can really insert a user in the DB / DONE
    public void insert(User user) {
        this.postgreConnection.openConnection();
        try {

            //this.stmt = this.c.createStatement();
            //ResultSet rs = this.stmt.executeQuery("INSERT INTO public.\"USER\" (email, password, name, phonenumber) VALUES ('aaaaas', 'sdgg', 'gdgsg', );");
            //rs.next();//
            //insert into public."USER" (email, password, name, phonenumber)  VALUES ("nathan@se.com","ruby","nathan","06666666");
            //rs.close();//

            String sql = "INSERT INTO public.\"USER\" (email, password, name, phonenumber) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = this.c.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getName());
            preparedStatement.setString(4,user.getPhoneNumber());
            preparedStatement.executeQuery();
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.postgreConnection.closeConnection();
        System.out.println("Operation done successfully");
    }


}
