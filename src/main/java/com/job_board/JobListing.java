package com.job_board;

import java.sql.*;
import java.util.Scanner;

public class JobListing {
    static void createJobListing(Connection con, Scanner scanner) throws SQLException {
        try {
            System.out.print("Enter company name: ");
            String companyName = scanner.nextLine().trim();
            System.out.print("Enter apply link: ");
            String applyLink = scanner.nextLine().trim();
            System.out.print("Enter job role: ");
            String jobRole = scanner.nextLine().trim();

            String sql = "INSERT INTO jobdetails (company_name, apply_link, job_role) VALUES (?, ?, ?)";
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
        } catch (Exception e) {}
    }

    static void deleteJobListing(Connection con, Scanner scanner) throws SQLException {
        try {
            System.out.print("Enter job ID to delete: ");
            String jobId = scanner.nextLine().trim();

            String deleteSql = "DELETE FROM jobdetails WHERE job_id = ?";
            try (PreparedStatement deleteStmt = con.prepareStatement(deleteSql)) {
                deleteStmt.setString(1, jobId);
                int rowsDeleted = deleteStmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Job listing deleted successfully.");
                } else {
                    System.out.println("Failed to delete job listing. Job ID not found.");
                }
            }
        } catch(Exception e) {}
    }
}
