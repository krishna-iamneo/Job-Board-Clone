import java.util.Scanner;

public class App {
    static final String ADMIN_USERNAME = "admin";
    static final String ADMIN_PASSWORD = "password";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Job Board Application!");
        System.out.println("Are you an admin or an applicant? (admin/applicant)");
        String userType = scanner.nextLine().trim();

        if (userType.equalsIgnoreCase("admin")) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String password = scanner.nextLine().trim();

            if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
                System.out.println("Login successful!");
                displayAdminMenu(scanner);
            } else {
                System.out.println("Invalid credentials. Please enter valid username and password.");
            }
        } else if (userType.equalsIgnoreCase("applicant")) {
            // Implement applicant functionalities
        } else {
            System.out.println("Invalid user type. Please enter 'admin' or 'applicant'.");
        }

        scanner.close();
    }

    static void displayAdminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nMenu of Functionalities:");
            System.out.println("1. Create Job");
            System.out.println("2. Edit Job");
            System.out.println("3. Delete Job");
            System.out.println("4. Search Job");
            System.out.println("5. View all Jobs");
            System.out.println("6. Exit");

            System.out.print("Enter the index of the desired functionality: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createJob();
                    break;
                case 2:
                    editJob();
                    break;
                case 3:
                    deleteJob();
                    break;
                case 4:
                    searchJob();
                    break;
                case 5:
                    viewAllJobs();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid index. Please enter a valid index.");
            }
        }
    }

    // Implement corresponding functionality methods
    static void createJob() {
        // Implementation
    }

    static void editJob() {
        // Implementation
    }

    static void deleteJob() {
        // Implementation
    }

    static void searchJob() {
        // Implementation
    }

    static void viewAllJobs() {
        // Implementation
    }
}
