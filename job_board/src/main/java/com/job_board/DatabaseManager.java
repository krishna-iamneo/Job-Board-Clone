package com.job_board;

import java.sql.*;

public class DatabaseManager {
    static final String DB_URL = "jdbc:mysql://localhost/";
    static final String USER = "root";
    static final String PASS = "examly";

    public static boolean databaseExists(String dbName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             ResultSet resultSet = conn.getMetaData().getCatalogs()) {
            while (resultSet.next()) {
                String databaseName = resultSet.getString(1);
                if (databaseName.equalsIgnoreCase(dbName)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createDatabase(String dbName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE " + dbName);
            System.out.println("Database '" + dbName + "' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}