package com.filmplanner.dao.postgre;

import com.filmplanner.dao.AbstractDAOFactory;
import com.filmplanner.dao.UserDAO;
import com.filmplanner.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreUserDAO implements UserDAO {


    private Connection connection;

    // The constructor must be package-private so only the PostgreDAOFactory can create a new instance.
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
        User foundUser = null;
        try {
            String query = "SELECT * FROM fp_user WHERE email='" + email + "'";
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phonenumber");
                String password = resultSet.getString("password");
                foundUser = new User(userId, name, email, phone, password);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUser;
    }


    /**
     * Creates a user in the database.
     *
     * @param user the user to insert
     */
    public void create(User user) {
        try {
            String query = "INSERT INTO fp_user (email, password, name, phonenumber) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getPhoneNumber());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        List<User> foundUsers = null;
        try {
            foundUsers = new ArrayList<>();
            String query = "SELECT user_id, name, email, phonenumber, password FROM fp_user";
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("user_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phonenumber");
                String password = resultSet.getString("password");
                foundUsers.add(new User(userId, name, email, phone, password));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundUsers;
    }
}
