package com.job_board;

import java.sql.*;
import java.util.Scanner;

public class JobSearch {
    static void searchJobListing(Connection con, Scanner scanner) throws SQLException {
        try {
            System.out.print("Enter job role to search: ");
            String jobRole = scanner.nextLine().trim();

            String selectSql = "SELECT * FROM jobdetails WHERE job_role LIKE ?";
            try (PreparedStatement pstmt = con.prepareStatement(selectSql)) {
                pstmt.setString(1, "%" + jobRole + "%");
                ResultSet rs = pstmt.executeQuery();
                boolean found = false;
                while (rs.next()) {
                    found = true;
                    System.out.println("\nJob Details:");
                    System.out.println("Job ID: " + rs.getString("job_id"));
                    System.out.println("Company Name: " + rs.getString("company_name"));
                    System.out.println("Job Role: " + rs.getString("job_role"));
                    System.out.println("Apply Link: " + rs.getString("apply_link"));
                    System.out.println("---------------------------");
                }
                if (!found) {
                    System.out.println("No job listings found for the provided job role.");
                }
            }
        } catch (Exception e) {}
    }
}
