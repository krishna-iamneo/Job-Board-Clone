import java.sql.*;

public class DatabaseCreation {
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/";
    static final String USER = "root";
    static final String PASS = "Valencia";
    static final String DB_NAME = "job_database"; 

    static {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            // Create the database if it does not exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            System.out.println("Database '" + DB_NAME + "' created successfully.");

            // Use the database
            stmt.executeUpdate("USE " + DB_NAME);

            // Create the jobs table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS jobs ("
                    + "job_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "job_role VARCHAR(255) NOT NULL,"
                    + "job_description TEXT,"
                    + "company_name VARCHAR(255) NOT NULL,"
                    + "apply_link VARCHAR(255) NOT NULL"
                    + ")";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table 'jobs' created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createDatabase();
    }
}
