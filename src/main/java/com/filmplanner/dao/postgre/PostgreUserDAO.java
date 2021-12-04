package com.filmplanner.dao.postgre;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

import java.sql.*;

public class PostgreUserDAO implements UserDAO {


    private Connection connection = null;
    private Statement stmt = null;
    private final PostgreConnection postgreConnection;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
    PostgreUserDAO(String url, String user, String password) {
        this.postgreConnection = new PostgreConnection(url, user, password);
    }

    /**
     * Finds a user based on its email address.
     *
     * @param email the user's email address
     * @return the user if the email exists; null otherwise
     */
    @Override
    public User find(String email) {
        // TODO write a generic method which can execute a SQL statement
        // TODO use the generic method to query the database
        User newUser = null;
        this.connection = this.postgreConnection.openConnection();
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
        this.postgreConnection.closeConnection();
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
        // TODO use the generic method to query the database
        String password = null;
        this.connection = this.postgreConnection.openConnection();
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

        this.postgreConnection.closeConnection();
        System.out.println("Operation done successfully");
        return password;

    }

    /**
     * Inserts a user in the database.
     *
     * @param user the user to insert
     */
    // TODO add the method into the UserDAO interface
    public void insert(User user) {
        // TODO use the generic method to query the database
        this.postgreConnection.openConnection();
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
        this.postgreConnection.closeConnection();
        System.out.println("Operation done successfully");
    }
}
