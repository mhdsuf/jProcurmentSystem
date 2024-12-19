package procurmentsystem;

import procurmentsystem.Table.InteractionsWithTable;
import procurmentsystem.Table.ValueNotFound;

import java.io.FileNotFoundException;

public abstract class User extends InteractionsWithTable {
    protected String email;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected Role role;

    public Role getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void updateEmail(String newEmail) {
        try {
            update("email", this.email, newEmail);  // InteractionsWithTable method
            this.email = newEmail;
        } catch (Exception e) {
            System.out.println("Error updating email.");
        }
    }

    public void updatePassword(String currentPassword, String newPassword) {
        if (this.password.equals(currentPassword)) {
            try {
                update("password", this.password, newPassword);
                this.password = newPassword;
            } catch (Exception e) {
                System.out.println("Error updating password.");
            }
        } else {
            System.out.println("Current password is incorrect.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", ID, email, password, firstName, lastName, role);
    }

    @Override
    protected boolean add() {
        try {
            String[] row = {ID, email, password, firstName, lastName, role.toString()};
            table.addRow(row);
            return true;
        } catch (Exception e) {
            System.out.println("Error adding user.");
            return false;
        }

    }

    public boolean delete() {
        try {
            int rowIndex = table.getRowIndex("ID", (x) -> x.equals(ID));
            table.deleteRow(rowIndex);  // InteractionsWithTable method
            System.out.println("Account deleted successfully.");
            return true;
        } catch (ValueNotFound e) {
            System.out.println("Error deleting account.");
            return false;
        }
    }
}