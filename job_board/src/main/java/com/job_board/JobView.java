package com.job_board;

import java.sql.*;
import java.util.Scanner;

public class JobView {
    static void viewAllJobListings(Connection con) throws SQLException {
        String selectSql = "SELECT * FROM jobdetails";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectSql)) {
            System.out.println("\nAll Job Listings:");
            while (rs.next()) {
                System.out.println("Job ID: " + rs.getString("job_id"));
                System.out.println("Company Name: " + rs.getString("company_name"));
                System.out.println("Apply Link: " + rs.getString("apply_link"));
                System.out.println("Job Role: " + rs.getString("job_role"));
                System.out.println("---------------------------");
            }
        }
    }

    static void viewJobDetails(Connection con, Scanner scanner) throws SQLException {
        try {
            System.out.print("Enter job ID to view details: ");
            String jobId = scanner.nextLine().trim();

            String selectSql = "SELECT * FROM jobdetails WHERE job_id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                pstmt.setString(1, jobId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    System.out.println("\nJob Details:");
                    System.out.println("Job ID: " + rs.getString("job_id"));
                    System.out.println("Company Name: " + rs.getString("company_name"));
                    System.out.println("Apply Link: " + rs.getString("apply_link"));
                    System.out.println("Job Role: " + rs.getString("job_role"));
                } else {
                    System.out.println("Job ID not found.");
                }
            }
        } catch(Exception e) {}
    }
}
