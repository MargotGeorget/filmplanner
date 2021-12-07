package com.filmplanner.dao.postgre;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

import java.sql.*;

public class PostgreUserDAO implements UserDAO {


    private Connection connection;
    private Statement stmt;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    // TODO pass the connection as a parameter
    // TODO remove useless parameters
    PostgreUserDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Finds a user based on its email address.
     *
     * @param email the user's email address
     * @return the user if the email exists; null otherwise
     */
    @Override
    public User find(String email) {
        User newUser = null;
        try {
            this.stmt = this.connection.createStatement();
            ResultSet rs = this.stmt.executeQuery("SELECT * FROM public.\"USER\" WHERE EMAIL='" + email + "';");
            rs.next();
            newUser = new User(rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("PHONENUMBER")); //TODO : Gérer l'erreur quand le login est pas bon
            rs.close();
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
        return newUser;

    }

    /**
     * Finds a user's password based on its email address.
     *
     * @param email the user's email address
     * @return the user's password if the email exists; null otherwise
     */
    @Override
    public String getPassword(String email) {
        String password = null;
        try {
            this.stmt = this.connection.createStatement();
            ResultSet rs = this.stmt.executeQuery("SELECT * FROM public.\"USER\" WHERE EMAIL='" + email + "';");
            rs.next();
            password = rs.getString("PASSWORD");
            rs.close();
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
        return password;

    }

    /**
     * Inserts a user in the database.
     *
     * @param user the user to insert
     */
    public void insert(User user) {
        try {
            String sql = "INSERT INTO public.\"USER\" (email, password, name, phonenumber) VALUES (?, ?, ?, ?);";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.executeQuery();
            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Operation done successfully");
    }
}
