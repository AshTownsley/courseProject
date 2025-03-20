package courseProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BankAcctGUI extends JFrame {
    private JTextField idField, ssnField, lastNameField, firstNameField, streetField,
            cityField, zipField, phoneField, balanceField, transactionAmountField;
    private JComboBox<String> stateBox;
    private JRadioButton checkingRadio, savingsRadio, depositRadio, withdrawRadio;
    private JLabel statusLabel, balanceLabel;
    private JButton addCustomerButton, displayButton, transactionButton, clearButton;
    private ArrayList<Customer> customers = new ArrayList<>();

    public BankAcctGUI() {
        setTitle("Bank Account Management");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(12, 2));

        // Input Fields
        add(new JLabel("Customer ID:"));
        idField = new JTextField();
        add(idField);

        add(new JLabel("SSN:"));
        ssnField = new JTextField();
        add(ssnField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Street:"));
        streetField = new JTextField();
        add(streetField);

        add(new JLabel("City:"));
        cityField = new JTextField();
        add(cityField);

        add(new JLabel("State:"));
        stateBox = new JComboBox<>(new String[]{"IL", "MO", "KS", "MD", "FL", "CA", "VA", "TX"});
        add(stateBox);

        add(new JLabel("ZIP:"));
        zipField = new JTextField();
        add(zipField);

        add(new JLabel("Phone:"));
        phoneField = new JTextField();
        add(phoneField);

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

        // Buttons
        addCustomerButton = new JButton("Add Customer & Account");
        add(addCustomerButton);
        displayButton = new JButton("Display Customer Data");
        add(displayButton);

        // Transaction Section
        add(new JLabel("Transaction Amount:"));
        transactionAmountField = new JTextField();
        add(transactionAmountField);

        add(new JLabel("Transaction Type:"));
        JPanel transactionPanel = new JPanel();
        depositRadio = new JRadioButton("Deposit");
        withdrawRadio = new JRadioButton("Withdraw");
        ButtonGroup transactionGroup = new ButtonGroup();
        transactionGroup.add(depositRadio);
        transactionGroup.add(withdrawRadio);
        transactionPanel.add(depositRadio);
        transactionPanel.add(withdrawRadio);
        add(transactionPanel);

        transactionButton = new JButton("Perform Transaction");
        add(transactionButton);

        // Display balance
        add(new JLabel("Balance:"));
        balanceLabel = new JLabel("$0.00");
        add(balanceLabel);

        // Status Message
        statusLabel = new JLabel("Status: Waiting for input...");
        add(statusLabel);

        // Clear Button
        clearButton = new JButton("Clear");
        add(clearButton);

        // Button Actions
        addCustomerButton.addActionListener(e -> addCustomer());
        displayButton.addActionListener(e -> displayCustomer());
        transactionButton.addActionListener(e -> performTransaction());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addCustomer() {
        try {
            String id = idField.getText().trim();
            String ssn = ssnField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String street = streetField.getText().trim();
            String city = cityField.getText().trim();
            String state = (String) stateBox.getSelectedItem();
            String zip = zipField.getText().trim();
            String phone = phoneField.getText().trim();

            if (id.isEmpty() || ssn.isEmpty() || lastName.isEmpty() || firstName.isEmpty() || street.isEmpty() ||
                    city.isEmpty() || zip.isEmpty() || phone.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);

            Account account;
            if (checkingRadio.isSelected()) {
                account = new CheckingAccount(id);
            } else if (savingsRadio.isSelected()) {
                account = new SavingsAccount(id);
            } else {
                throw new IllegalArgumentException("Select an account type.");
            }

            customer.setAccount(account);
            customers.add(customer);
            statusLabel.setText("Customer and account added successfully!");
        } catch (Exception ex) {
            statusLabel.setText("Error: " + ex.getMessage());
        }
    }

    private void displayCustomer() {
        String id = idField.getText().trim();
        for (Customer c : customers) {
            if (c.getCustomerID().equals(id)) {
                statusLabel.setText("Customer Found: " + c.toString());
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
        ssnField.setText("");
        lastNameField.setText("");
        firstNameField.setText("");
        streetField.setText("");
        cityField.setText("");
        zipField.setText("");
        phoneField.setText("");
        transactionAmountField.setText("");
        checkingRadio.setSelected(false);
        savingsRadio.setSelected(false);
        depositRadio.setSelected(false);
        withdrawRadio.setSelected(false);
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

