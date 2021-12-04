package com.filmplanner.dao.postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreConnection {

    private Connection connection = null;
    private final Statement stmt = null;
    private final String url;
    private final String password;
    private final String user;


    public PostgreConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Opens the connection to the database and returns it.
     *
     * @return the connection to the database
     */
    protected Connection openConnection() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            this.connection.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (Exception e) {
            e.printStackTrace();
            // TODO manage error more elegantly
            //System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(1);
        }
        return this.connection;
    }

    /**
     * Closes the database connection.
     */
    protected void closeConnection() {
        try {
            this.connection.close();
            System.out.println("Close database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
