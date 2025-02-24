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

                Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);
                System.out.println("Customer added successfully!");

                System.out.println("\nEnter Account Details for " + firstName + " " + lastName + ":");

                String accountNumber = DataEntry.getStringWithLimit("Account Number (max 5 chars): ", 5);
                String accountType;
                while (true) {
                    accountType = DataEntry.getString("Account Type (CHK or SAV): ").toUpperCase();
                    if (accountType.equals("CHK") || accountType.equals("SAV")) break;
                    System.out.println("Invalid Account Type. Enter 'CHK' or 'SAV'.");
                }

                double serviceFee = DataEntry.getDoubleInRange("Service Fee ($0.00 - $10.00): ", 0, 10);
                double interestRate = DataEntry.getDoubleInRange("Interest Rate (0% - 10%): ", 0, 10);
                double overdraftFee = DataEntry.getDoubleInRange("Overdraft Fee: ", 0, Double.MAX_VALUE);

                Account account = new Account(accountNumber, accountType, serviceFee, interestRate, overdraftFee);
                System.out.println("Account created successfully!");

                customers.add(customer);
                System.out.print("Add another customer? (yes/no): ");
                moreCustomers = DataEntry.getString("").equalsIgnoreCase("yes");

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\nAll Customers and Accounts:");
        for (Customer c : customers) {
            System.out.println(c);
        }
    }
}
