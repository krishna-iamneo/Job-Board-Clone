package com.job_board;

import java.sql.Connection;
import java.util.Scanner;

public class AdminOperations {

    public static void performAdminOperations(Connection conn) {
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;
        int choice;
        while (!exitMenu) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Job Listing");
            System.out.println("2. Delete Job Listing");
            System.out.println("3. View All Job Listings");
            System.out.println("4. View Job Details");            
            System.out.println("5. Search Job by role"); 
            System.out.println("6. Exit");

           System.out.println("Enter the choice");
           choice=scanner.nextInt();
            try {
                switch (choice) {
                    case 1:
                        scanner.nextLine();
                        JobListing.createJobListing(conn, scanner);
                        break;
                    case 2:
                        scanner.nextLine();
                        JobListing.deleteJobListing(conn, scanner);
                        break;
                    case 3:
                        scanner.nextLine();
                        JobView.viewAllJobListings(conn);
                        break;
                    case 4:
                        scanner.nextLine();
                        JobView.viewJobDetails(conn, scanner);
                        break;
                    case 5:
                        scanner.nextLine();
                        JobSearch.searchJobListing(conn, scanner);
                        break;
                    case 6:
                        scanner.nextLine();
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
