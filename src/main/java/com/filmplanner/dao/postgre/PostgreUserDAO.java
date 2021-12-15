package com.filmplanner.dao.postgre;

import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public User findByEmail(String email) {
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
     * Finds a user based on its id.
     *
     * @param id the user's id
     * @return the user if the user exists; null otherwise
     */
    @Override
    public User findById(int id) {
        User newUser = null;
        try {
            this.stmt = this.connection.createStatement();
            ResultSet rs = this.stmt.executeQuery("SELECT * FROM public.fp_user WHERE USER_ID='" + id + "';");
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

    /** NOT NEEDed
     * Finds a user's password based on its email address.
     *
     * @param email the user's email address
     * @return the user's password if the email exists; null otherwise

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
     */
    /**
     * update a user by providing its id
     * @param id
     * @param updatedUser
     * @return User the updated user object
     */
    @Override
    public long update(int id, User updatedUser) {

        //TODO: modifier fonction pour faire update
        String sql = "UPDATE fp_user SET email=?, password=?, name=?, phonenumber=? WHERE user_id=?;";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, updatedUser.getEmail());
            preparedStatement.setString(2, updatedUser.getPassword());
            preparedStatement.setString(3, updatedUser.getName());
            preparedStatement.setString(4, updatedUser.getPhoneNumber());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Update error !");
            e.printStackTrace();
            return 0;
        }
        System.out.println("Update done successfully");
        return 1;


    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM fp_user;");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                User user = new User(rs.getString("NAME"), rs.getString("EMAIL"), rs.getString("PASSWORD"), rs.getString("PHONENUMBER"), rs.getInt("user_id"));
                users.add(user);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("All users returned successfully");
        return users;
    }

    /**
     * Inserts a user in the database.
     * @param user the user to insert
     * @return int : 0 if failed, 1 if successful
     */
    public int create(User user) {
        long id = 0;
        String sql = "INSERT INTO public.fp_user (email,password,name,phonenumber) VALUES(?,?,?,?)";
        System.out.println(user.getName() + " - " + user.getEmail() + " - " + user.getPhoneNumber() + " - " + user.getPassword());
        //String sql = "INSERT INTO client (company_name,description,referee_name, referee_email,referee_tel) VALUES(?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Insertion error !");
            e.printStackTrace();
            return 0;
        }
        System.out.println("Operation done successfully");
        return 1;
    }

    /**
     * delete a user by its id
     * @param userId
     * @return int 1 if success, 0 otherwise
     */
    @Override
    public int deleteById(int userId) {

        try {
            this.stmt = this.connection.createStatement();
            this.stmt.executeUpdate("DELETE FROM fp_user WHERE user_id ='" + userId + "';");

            this.stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        System.out.println("Operation done successfully");
        return 1;


    }
}
