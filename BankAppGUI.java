package courseProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BankAcctGUI extends JFrame {
    private JTextField idField, transactionAmountField;
    private JRadioButton checkingRadio, savingsRadio, depositRadio, withdrawRadio;
    private JLabel statusLabel, balanceLabel;
    private JButton addCustomerButton, displayButton, transactionButton, clearButton;
    private ArrayList<Customer> customers = new ArrayList<>();

    public BankAcctGUI() {
        setTitle("Bank Account Management");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(10, 2));

        // Customer ID Field
        add(new JLabel("Customer ID:"));
        idField = new JTextField();
        add(idField);

        // Account Type Selection
        add(new JLabel("Account Type:"));
        JPanel accountPanel = new JPanel();
        checkingRadio = new JRadioButton("Checking");
        savingsRadio = new JRadioButton("Savings");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(checkingRadio);
        accountGroup.add(savingsRadio);
        accountPanel.add(checkingRadio);
        accountPanel.add(savingsRadio);
        add(accountPanel);

        // Add Customer Button
        addCustomerButton = new JButton("Add Customer & Account");
        add(addCustomerButton);

        // Display Customer Button
        displayButton = new JButton("Display Customer Data");
        add(displayButton);

        // Transaction Amount Field
        add(new JLabel("Transaction Amount:"));
        transactionAmountField = new JTextField();
        add(transactionAmountField);

        // Transaction Type Selection (Deposit/Withdraw)
        add(new JLabel("Transaction Type:"));
        JPanel transactionPanel = new JPanel();
        depositRadio = new JRadioButton("Deposit");
        withdrawRadio = new JRadioButton("Withdraw");
        ButtonGroup transactionGroup = new ButtonGroup();
        transactionGroup.add(depositRadio);
        transactionGroup.add(withdrawRadio);
        transactionPanel.add(depositRadio);
        transactionPanel.add(withdrawRadio);
        add(transactionPanel);  // Ensuring the transaction panel is added correctly

        // Perform Transaction Button
        transactionButton = new JButton("Perform Transaction");
        add(transactionButton);

        // Balance Display
        add(new JLabel("Balance:"));
        balanceLabel = new JLabel("$0.00");
        add(balanceLabel);

        // Status Message
        statusLabel = new JLabel("Status: Waiting for input...");
        add(statusLabel);

        // Clear Button
        clearButton = new JButton("Clear");
        add(clearButton);

        // Set Default Selection
        depositRadio.setSelected(true);  // Ensure at least one transaction type is selected

        // Button Actions
        addCustomerButton.addActionListener(e -> addCustomer());
        displayButton.addActionListener(e -> displayCustomer());
        transactionButton.addActionListener(e -> performTransaction());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addCustomer() {
        try {
            String id = idField.getText().trim();
            if (id.isEmpty()) throw new IllegalArgumentException("Customer ID cannot be empty.");

            Account account;
            if (checkingRadio.isSelected()) {
                account = new CheckingAccount(id);
            } else if (savingsRadio.isSelected()) {
                account = new SavingsAccount(id);
            } else {
                throw new IllegalArgumentException("Select an account type.");
            }

            Customer customer = new Customer(id, "123456789", "Doe", "John", "123 Street", "City", "IL", "12345", "1234567890");
            customer.setAccount(account);
            customers.add(customer);

            statusLabel.setText("Customer added successfully!");
        } catch (Exception ex) {
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }

    private void displayCustomer() {
        String id = idField.getText().trim();
        for (Customer c : customers) {
            if (c.getCustomerID().equals(id)) {
                statusLabel.setText("Customer Found: " + c.getFirstName() + " " + c.getLastName());
                balanceLabel.setText(String.format("$%.2f", c.getAccount().getBalance()));
                return;
            }
        }
        statusLabel.setText("Customer not found.");
    }

    private void performTransaction() {
        try {
            String id = idField.getText().trim();
            double amount = Double.parseDouble(transactionAmountField.getText().trim());

            for (Customer c : customers) {
                if (c.getCustomerID().equals(id)) {
                    Account account = c.getAccount();
                    if (depositRadio.isSelected()) {
                        account.deposit(amount);
                        statusLabel.setText("Deposit successful.");
                    } else if (withdrawRadio.isSelected()) {
                        account.withdrawal(amount);
                        statusLabel.setText("Withdrawal successful.");
                    } else {
                        throw new IllegalArgumentException("Select a transaction type.");
                    }
                    balanceLabel.setText(String.format("$%.2f", account.getBalance()));
                    return;
                }
            }
            statusLabel.setText("Customer not found.");
        } catch (Exception ex) {
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        idField.setText("");
        transactionAmountField.setText("");
        depositRadio.setSelected(true);
        balanceLabel.setText("$0.00");
        statusLabel.setText("Cleared.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAcctGUI frame = new BankAcctGUI();
            frame.setVisible(true);
        });
    }
}
