import java.util.ArrayList;
import java.util.Scanner;

public class BankAcctApp {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean moreCustomers = true;

        while (moreCustomers) {
            try {
                // Collect Customer Information
                System.out.println("\nEnter Customer Details:");
                String id = DataEntry.getStringWithLimit("Customer ID (max 5 chars): ", 5);
                String ssn = DataEntry.getNumericString("SSN (9 digits): ", 9);
                String lastName = DataEntry.getStringWithLimit("Last Name (max 20 chars): ", 20);
                String firstName = DataEntry.getStringWithLimit("First Name (max 15 chars): ", 15);
                String street = DataEntry.getStringWithLimit("Street (max 20 chars): ", 20);
                String city = DataEntry.getStringWithLimit("City (max 20 chars): ", 20);
                String state = DataEntry.getState("State (2-letter code): ");
                String zip = DataEntry.getNumericString("ZIP Code (5 digits): ", 5);
                String phone = DataEntry.getNumericString("Phone Number (10 digits): ", 10);

                Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);
                customers.add(customer);

                // Select Account Type
                Account account = null;
                while (true) {
                    System.out.println("\nSelect Account Type (CHK for Checking, SAV for Savings): ");
                    String accountType = scanner.nextLine().trim().toUpperCase();

                    if (accountType.equals("CHK")) {
                        account = new CheckingAccount(id);
                        break;
                    } else if (accountType.equals("SAV")) {
                        account = new SavingsAccount(id);
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter CHK or SAV.");
                    }
                }

                // Set the account for the customer
                customer.setAccount(account);
                System.out.println("Account created successfully!");

                // Perform Transactions
                boolean moreTransactions = true;
                while (moreTransactions) {
                    System.out.println("\nChoose Transaction: 1 - Deposit, 2 - Withdraw, 3 - Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 1) {
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        account.deposit(amount);
                    } else if (choice == 2) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        account.withdrawal(amount);
                    } else if (choice == 3) {
                        moreTransactions = false;
                    } else {
                        System.out.println("Invalid option. Try again.");
                    }
                }

                // Ask if another customer should be added
                System.out.print("Add another customer? (yes/no): ");
                moreCustomers = scanner.next().equalsIgnoreCase("yes");
                scanner.nextLine(); // Consume newline

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Display Customers and Accounts (Only the chosen account type)
        System.out.println("\nAll Customers and Their Accounts:");
        for (Customer c : customers) {
            System.out.println(c);
            Account acc = c.getAccount();
            if (acc instanceof CheckingAccount) {
                System.out.println("Checking Account Balance: $" + acc.balance());
            } else if (acc instanceof SavingsAccount) {
                System.out.println("Savings Account Balance: $" + acc.balance());
            }
        }
    }
}
