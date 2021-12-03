package com.filmplanner.dao.postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreConnection {
    private Connection c = null;
    private Statement stmt = null;
    private String url;
    private String password;
    private String user;


    public PostgreConnection(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    // TODO move method into a class accessible for all DAOs /DONE
    protected Connection openConnection() {
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

    // TODO move method into a class accessible for all DAOs / DONE
    protected void closeConnection() {
        try {
            this.c.close();
            System.out.println("Close database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
