package courseProject;

import java.util.ArrayList;
import java.util.Scanner;

public class BankAcctApp {
    public static void main(String[] args) {
        ArrayList<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean moreCustomers = true;

        while (moreCustomers) {
            try {
                // Input customer details
                System.out.println("\nEnter Customer Details:");
                String id = DataEntry.getStringWithLimit("Customer ID (max 5 chars): ", 5);
                String ssn = DataEntry.getNumericString("SSN (9 digits): ", 9);
                String lastName = DataEntry.getStringWithLimit("Last Name (max 20 chars): ", 20);
                String firstName = DataEntry.getStringWithLimit("First Name (max 15 chars): ", 15);
                String street = DataEntry.getStringWithLimit("Street Address (max 20 chars): ", 20);
                String city = DataEntry.getStringWithLimit("City (max 20 chars): ", 20);
                String state = DataEntry.getStringWithLimit("State (2-letter abbreviation): ", 2);
                String zip = DataEntry.getNumericString("ZIP (5 digits): ", 5);
                String phone = DataEntry.getNumericString("Phone Number (10 digits): ", 10);

                // Create customer and add to list
                Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);
                customers.add(customer);

                // Select account type
                System.out.println("\nSelect Account Type (CHK for Checking, SAV for Savings): ");
                String accountType;
                Account account = null;
                while (true) {
                    accountType = scanner.nextLine().toUpperCase();
                    if (accountType.equals("CHK")) {
                        account = new CheckingAccount(id);  // Create CheckingAccount
                        break;
                    } else if (accountType.equals("SAV")) {
                        account = new SavingsAccount(id);  // Create SavingsAccount
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter CHK or SAV.");
                    }
                }

                customer.setAccount(account);  // Associate the account with the customer
                System.out.println("Account created successfully!");

                // Perform Transactions
                boolean moreTransactions = true;
                while (moreTransactions) {
                    System.out.println("\nChoose Transaction: 1 - Deposit, 2 - Withdraw, 3 - Exit");
                    int choice = scanner.nextInt();
                    if (choice == 1) {
                        System.out.print("Enter deposit amount: ");
                        double amount = scanner.nextDouble();
                        account.deposit(amount);
                        System.out.printf("Transaction complete. Balance: $%.2f\n", account.getBalance());
                    } else if (choice == 2) {
                        System.out.print("Enter withdrawal amount: ");
                        double amount = scanner.nextDouble();
                        account.withdrawal(amount);
                        System.out.printf("Transaction complete. Balance: $%.2f\n", account.getBalance());
                    } else if (choice == 3) {
                        // Apply interest when exiting
                        account.applyInterest();
                        System.out.printf("Account balance after interest: $%.2f\n", account.getBalance());
                        moreTransactions = false;
                    } else {
                        System.out.println("Invalid option. Try again.");
                    }
                }

                // Ask if another customer should be added
                System.out.print("Add another customer? (yes/no): ");
                moreCustomers = scanner.next().equalsIgnoreCase("yes");
                scanner.nextLine();  // Consume the newline character after reading yes/no

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Display all customers and accounts
        System.out.println("\nAll Customers and Accounts:");
        for (Customer c : customers) {
            System.out.println(c);  // Display customer details
            System.out.printf("Balance after interest: $%.2f\n", c.getAccount().getBalance());  // Display balance
        }
    }
}
