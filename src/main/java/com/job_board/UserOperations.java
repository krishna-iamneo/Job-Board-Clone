package com.job_board;

import java.sql.Connection;
import java.util.Scanner;

public class UserOperations {

    public static void performUserOperations(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;

        while (!exitMenu) {
            System.out.println("Choose an option:");
            System.out.println("1. View All Job Listings");
            System.out.println("2. View Job Details");            
            System.out.println("3. Search Job by role");            
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            try {
                switch (choice) {
                    case 1:
                        JobView.viewAllJobListings(conn);
                        break;
                    case 2:
                        JobView.viewJobDetails(conn, scanner);
                        break;
                    case 3:
                        JobSearch.searchJobListing(conn, scanner);
                        break;
                    case 4:
                        exitMenu = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }
}