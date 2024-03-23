package com.job_board;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
 
public class App {
    static final String DB_NAME = "JobNestDB";
 
    public static void main(String[] args) {
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);
        try {
            // Check if the database exists, if not, create it
            if (!DatabaseManager.databaseExists(DB_NAME)) {
                DatabaseManager.createDatabase(DB_NAME);
            }
 
            // Connect to the database
            String dbUrl = "jdbc:mysql://localhost/" + DB_NAME;
            conn = DriverManager.getConnection(dbUrl, DatabaseManager.USER, DatabaseManager.PASS);
            System.out.println("Connected to database");
 
            // Create table if not exists
            TableCreator.createTableIfNotExists(conn);
 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                scanner.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
