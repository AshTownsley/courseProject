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
                String state = DataEntry.getState("State (2-letter code): ");
                String zip = DataEntry.getNumericString("ZIP Code (5 digits): ", 5);
                String phone = DataEntry.getNumericString("Phone Number (10 digits): ", 10);
                
                // Create Customer
                Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);
                
                // Check if Customer Already Exists
                boolean exists = false;
                for (Customer c : customers) {
                    if (c.getCustomerID().equals(id)) {
                        exists = true;
                        break;
                    }
                }

                if (exists) {
                    System.out.println("Error: A customer with this ID already exists. Try again.");
                    continue;
                }

                customers.add(customer);

                // Select Account Type
                System.out.println("\nSelect Account Type (CHK for Checking, SAV for Savings): ");
                Account account = null;
                while (account == null) {
                    String accountType = scanner.nextLine().toUpperCase();
                    if (accountType.equals("CHK")) {
                        account = new CheckingAccount(id);
                    } else if (accountType.equals("SAV")) {
                        account = new SavingsAccount(id);
                    } else {
                        System.out.println("Invalid choice. Please enter CHK or SAV.");
                    }
                }

                customer.setAccount(account);
                System.out.println("Account created successfully!");

                // Perform Transactions
                boolean moreTransactions = true;
                while (moreTransactions) {
                    try {
                        System.out.println("\nChoose Transaction: 1 - Deposit, 2 - Withdraw, 3 - Exit");
                        int choice = scanner.nextInt();
                        if (choice == 1) {
                            System.out.print("Enter deposit amount: ");
                            double amount = scanner.nextDouble();
                            account.deposit(amount);
                            System.out.println("Deposit successful! New balance: $" + account.balance());
                        } else if (choice == 2) {
                            System.out.print("Enter withdrawal amount: ");
                            double amount = scanner.nextDouble();
                            account.withdrawal(amount);
                            System.out.println("Withdrawal successful! New balance: $" + account.balance());
                        } else if (choice == 3) {
                            moreTransactions = false;
                        } else {
                            System.out.println("Invalid option. Try again.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        scanner.nextLine(); // Clear scanner buffer
                    }
                }

                System.out.print("Add another customer? (yes/no): ");
                moreCustomers = scanner.next().equalsIgnoreCase("yes");
                scanner.nextLine(); // Consume newline

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Display all customers and their accounts
        System.out.println("\nAll Customers and Accounts:");
        for (Customer c : customers) {
            System.out.println(c);
            if (c.getAccount() != null) {
                System.out.println("Balance after interest: $" + c.getAccount().balance());
            } else {
                System.out.println("No account assigned.");
            }
        }

        scanner.close(); // Close scanner to prevent resource leaks
    }
}
