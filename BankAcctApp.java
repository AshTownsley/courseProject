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
                System.out.println("\nEnter Customer Details:");
                String id = DataEntry.getStringWithLimit("Customer ID (max 5 chars): ", 5);
                String ssn = DataEntry.getNumericString("SSN (9 digits): ", 9);
                String lastName = DataEntry.getStringWithLimit("Last Name (max 20 chars): ", 20);
                String firstName = DataEntry.getStringWithLimit("First Name (max 15 chars): ", 15);
                String street = DataEntry.getStringWithLimit("Street (max 20 chars): ", 20);
                String city = DataEntry.getStringWithLimit("City (max 20 chars): ", 20);
                String state = DataEntry.getState("State (2 chars): ");
                String zip = DataEntry.getNumericString("ZIP (5 digits): ", 5);
                String phone = DataEntry.getNumericString("Phone (10 digits): ", 10);
                
                Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);
                customers.add(customer);

                System.out.println("\nSelect Account Type (CHK for Checking, SAV for Savings): ");
                String accountType;
                Account account = null;
                while (true) {
                    accountType = scanner.nextLine().toUpperCase();
                    if (accountType.equals("CHK")) {
                        account = new CheckingAccount(id);  // Create a CheckingAccount
                        break;
                    } else if (accountType.equals("SAV")) {
                        account = new SavingsAccount(id);  // Create a SavingsAccount
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter CHK or SAV.");
                    }
                }

                customer.setAccount(account);
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
                        // Apply interest before exit
                        customer.applyInterestToAccount();
                        System.out.printf("Interest applied. Final balance: $%.2f\n", account.getBalance());
                        moreTransactions = false;
                    } else {
                        System.out.println("Invalid option. Try again.");
                    }
                }

                System.out.print("Add another customer? (yes/no): ");
                moreCustomers = scanner.next().equalsIgnoreCase("yes");
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

