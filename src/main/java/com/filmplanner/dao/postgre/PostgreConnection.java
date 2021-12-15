package com.filmplanner.dao.postgre;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreConnection {

    private Connection connection;
    private final String url;
    private final String password;
    private final String user;
    private static PostgreConnection instance;


    public PostgreConnection() {
        Dotenv dotenv = Dotenv.load();
        this.user = dotenv.get("DB_USER");
        this.url = dotenv.get("DB_URL");
        this.password = dotenv.get("DB_PASSWORD");
    }

    /**
     * Gets the single PostgreConnection instance.
     *
     * @return the single PostgreConnection instance
     */
    public static PostgreConnection getInstance() {
        if (instance == null) {
            instance = new PostgreConnection();
        }
        return instance;
    }

    /**
     * Gets the connection to the database.
     *
     * @return the connection to the database
     */
    public Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                // opens connection
                this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * Closes the database connection.
     */
    protected void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
