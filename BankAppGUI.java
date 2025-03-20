package courseProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BankAppGUI extends JFrame {
    private JTextField idField, firstNameField, lastNameField, streetField, zipField, phoneField, transactionAmountField;
    private JRadioButton checkingRadio, savingsRadio, depositRadio, withdrawRadio;
    private JComboBox<String> stateDropdown;
    private JLabel statusLabel, balanceLabel;
    private JButton addCustomerButton, displayButton, transactionButton, clearButton;
    private ArrayList<Customer> customers = new ArrayList<>();

    public BankAppGUI() {
        setTitle("Bank Account Management");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Padding

        int row = 0;

        // --- Customer Information Fields ---
        addLabel("Customer ID:", row);
        idField = addTextField(row++);

        addLabel("First Name:", row);
        firstNameField = addTextField(row++);

        addLabel("Last Name:", row);
        lastNameField = addTextField(row++);

        addLabel("Street Address:", row);
        streetField = addTextField(row++);

        addLabel("ZIP Code:", row);
        zipField = addTextField(row++);

        addLabel("Phone Number:", row);
        phoneField = addTextField(row++);

        addLabel("State:", row);
        String[] states = {"IL", "MO", "KS", "MD", "FL", "CA", "VA", "TX"};
        stateDropdown = new JComboBox<>(states);
        add(stateDropdown, row++);

        // --- Account Type Selection ---
        addLabel("Account Type:", row);
        JPanel accountPanel = new JPanel();
        checkingRadio = new JRadioButton("Checking");
        savingsRadio = new JRadioButton("Savings");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(checkingRadio);
        accountGroup.add(savingsRadio);
        accountPanel.add(checkingRadio);
        accountPanel.add(savingsRadio);
        add(accountPanel, row++);

        // --- Buttons for Adding and Displaying Customers ---
        addCustomerButton = new JButton("Add Customer & Account");
        addComponent(addCustomerButton, row++);

        displayButton = new JButton("Display Customer Data");
        addComponent(displayButton, row++);

        // --- Transaction Section ---
        addLabel("Transaction Amount:", row);
        transactionAmountField = addTextField(row++);

        addLabel("Transaction Type:", row);
        JPanel transactionPanel = new JPanel();
        depositRadio = new JRadioButton("Deposit");
        withdrawRadio = new JRadioButton("Withdraw");
        ButtonGroup transactionGroup = new ButtonGroup();
        transactionGroup.add(depositRadio);
        transactionGroup.add(withdrawRadio);
        transactionPanel.add(depositRadio);
        transactionPanel.add(withdrawRadio);
        add(transactionPanel, row++);

        // --- Perform Transaction Button ---
        transactionButton = new JButton("Perform Transaction");
        addComponent(transactionButton, row++);

        // --- Balance Display ---
        addLabel("Balance:", row);
        balanceLabel = new JLabel("$0.00");
        add(balanceLabel, row++);

        // --- Status Message ---
        statusLabel = new JLabel("Status: Waiting for input...");
        addComponent(statusLabel, row++);

        // --- Clear Button ---
        clearButton = new JButton("Clear");
        addComponent(clearButton, row++);

        // Set Default Selection for Transaction Type
        depositRadio.setSelected(true);

        // --- Button Actions ---
        addCustomerButton.addActionListener(e -> addCustomer());
        displayButton.addActionListener(e -> displayCustomer());
        transactionButton.addActionListener(e -> performTransaction());
        clearButton.addActionListener(e -> clearFields());
    }

    private void addLabel(String text, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel(text), gbc);
    }

    private JTextField addTextField(int row) {
        JTextField textField = new JTextField(15);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(textField, gbc);
        return textField;
    }

    private void addComponent(JComponent component, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(component, gbc);
    }

    private void addCustomer() {
        try {
            String id = idField.getText().trim();
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String street = streetField.getText().trim();
            String zip = zipField.getText().trim();
            String phone = phoneField.getText().trim();
            String state = (String) stateDropdown.getSelectedItem();

            if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || street.isEmpty() || zip.isEmpty() || phone.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            Account account;
            if (checkingRadio.isSelected()) {
                account = new CheckingAccount(id);
            } else if (savingsRadio.isSelected()) {
                account = new SavingsAccount(id);
            } else {
                throw new IllegalArgumentException("Select an account type.");
            }

            Customer customer = new Customer(id, "123456789", lastName, firstName, street, "City", state, zip, phone);
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
                        if (account instanceof CheckingAccount && amount > account.getBalance()) {
                            statusLabel.setText("Withdraw unsuccessful, fee charged");
                        } else {
                            account.withdrawal(amount);
                            statusLabel.setText("Withdrawal successful.");
                        }
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
        firstNameField.setText("");
        lastNameField.setText("");
        streetField.setText("");
        zipField.setText("");
        phoneField.setText("");
        transactionAmountField.setText("");
        depositRadio.setSelected(true);
        balanceLabel.setText("$0.00");
        statusLabel.setText("Cleared.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankAppGUI frame = new BankAppGUI();
            frame.setVisible(true);
        });
    }
}
