import java.sql.*;
import java.util.Scanner;

public class JobListing {

    static final String DB_URL = "jdbc:mysql://localhost:3306/job_database";
    static final String USER = "root";
    static final String PASS = "Valencia";

    static void createJobListing(Connection con, Scanner scanner) throws SQLException {
        System.out.print("Enter company name: ");
        String companyName = scanner.nextLine().trim();
        System.out.print("Enter apply link: ");
        String applyLink = scanner.nextLine().trim();
        System.out.print("Enter job role: ");
        String jobRole = scanner.nextLine().trim();

        String sql = "INSERT INTO jobs (company_name, apply_link, job_role) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, companyName);
            pstmt.setString(2, applyLink);
            pstmt.setString(3, jobRole);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Job listing created successfully.");
            } else {
                System.out.println("Failed to create job listing.");
            }
        }
    }

    static void deleteJobListing(Connection con, Scanner scanner) throws SQLException {
        try {
            // Fetch all job IDs
            String fetchJobIdsSql = "SELECT job_id FROM jobs";
            try (Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery(fetchJobIdsSql)) {
                System.out.println("Available Job IDs:");
                while (rs.next()) {
                    System.out.println(rs.getInt("job_id"));
                }
            }
    
            // Prompt user for job ID to delete
            System.out.print("Enter job ID to delete: ");
            int jobId;
            try {
                jobId = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid job ID format.");
                return;
            }
    
            // Delete the selected job
            String deleteSql = "DELETE FROM jobs WHERE job_id = ?";
            try (PreparedStatement deleteStmt = con.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, jobId);
                int rowsDeleted = deleteStmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Job listing deleted successfully.");
                } else {
                    System.out.println("Failed to delete job listing. Job ID not found.");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in);
             Connection con = DriverManager.getConnection(DB_URL, USER, PASS)) {
    
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Create Job");
                System.out.println("2. Delete Job");
                
                System.out.print("Enter your choice: ");
    
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        createJobListing(con, scanner);
                        break;
                    case 2:
                        deleteJobListing(con, scanner);
                        break;
                
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
