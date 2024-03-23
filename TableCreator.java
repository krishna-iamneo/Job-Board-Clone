import java.sql.*;
 
public class TableCreator {
    static void createTableIfNotExists(Connection con) throws SQLException {
        // Define the SQL statement for creating the table if it doesn't exist
        String sql = "CREATE TABLE IF NOT EXISTS jobdetails (" +
                     "job_id INT AUTO_INCREMENT PRIMARY KEY," + 
                     "company_name VARCHAR(100)," +
                     "job_role VARCHAR(100)," + 
                     "apply_link VARCHAR(500)" +
                     ")";
        // Execute the SQL statement
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'jobdetails' created successfully (if it didn't exist already).");
        } catch (SQLException e) {
            System.out.println("Failed to create table: " + e.getMessage());
        }
    }
}
