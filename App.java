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
            // Login system
            System.out.println("Welcome to Job Board!");
            System.out.print("Are you an admin? (yes/no): ");
            String userType = scanner.nextLine().toLowerCase();
            if (userType.equals("yes")) {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                // Validate admin credentials
                if (username.equals("admin") && password.equals("admin@123")) {
                    // Perform admin operations
                    AdminOperations.performAdminOperations(conn);
                } else {
                    System.out.println("Invalid username or password. Access denied.");
                }
            } 
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
