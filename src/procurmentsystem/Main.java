package procurmentsystem;

import java.io.FileNotFoundException;
import java.util.Scanner;
import procurmentsystem.Table.Table;
import procurmentsystem.Table.ValueNotFound;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Assuming that the table is initialized with the CSV file (in your case, the table is handling user data)
        Table table = new Table("src/files/users.csv");  // Initialize the Table with the CSV file

        // Display a welcome message
        System.out.println("Welcome to the Procurement System!");

        // Let the user login
        User loggedInUser = User.login(table);  // Get the logged-in user based on the table

        // After login, direct the user based on their role
        if (loggedInUser.role == Role.Admin) {
            // Admin has access to admin options
            User.adminOptions(loggedInUser);
        } else {
            // Non-admins have limited options
            User.nonAdminOptions(loggedInUser);
        }
    }
}
