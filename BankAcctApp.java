package courseProject;

import java.util.ArrayList;

public class BankAcctApp {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        boolean moreCustomers = true;

        while (moreCustomers) {
            try {
                System.out.println("\nEnter Customer Details:");
                String id = DataEntry.getStringWithLimit("Customer ID (max 5 chars): ", 5);
                String ssn = DataEntry.getNumericString("SSN (9 digits): ", 9);
                String lastName = DataEntry.getStringWithLimit("Last Name (max 20 chars): ", 20);
                String firstName = DataEntry.getStringWithLimit("First Name (max 15 chars): ", 15);
                String street = DataEntry.getStringWithLimit("Street (max 20 chars): ", 20);
                String city = DataEntry.getStringWithLimit("City (max 20 chars): ", 20);
                String state = DataEntry.getStringWithLimit("State (2 chars): ", 2);
                String zip = DataEntry.getNumericString("ZIP (5 digits): ", 5);
                String phone = DataEntry.getNumericString("Phone (10 digits): ", 10);

                customers.add(new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone));
                System.out.println("Customer added successfully!");

                System.out.print("Add another customer? (yes/no): ");
                String response = DataEntry.getString("");
                moreCustomers = response.equalsIgnoreCase("yes");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nAll Customers:");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}
