package courseProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankAppGUI {
    private JFrame frame;
    private JTextField idField, ssnField, lastNameField, firstNameField, streetField, cityField, stateField, zipField, phoneField, amountField;
    private JComboBox<String> stateDropdown;
    private JRadioButton checkingButton, savingsButton, depositButton, withdrawButton;
    private JButton addCustomerButton, displayButton, transactionButton, clearButton;
    private JTextArea outputArea;
    private ArrayList<Customer> customers;
    
    public BankAppGUI() {
        customers = new ArrayList<>();
        frame = new JFrame("Bank Account Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new GridLayout(12, 2, 5, 5));
        frame.getContentPane().setBackground(new Color(200, 255, 200));
        
        // Labels and Input Fields
        frame.add(new JLabel("Customer ID:"));
        idField = new JTextField();
        frame.add(idField);
        
        frame.add(new JLabel("SSN:"));
        ssnField = new JTextField();
        frame.add(ssnField);
        
        frame.add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        frame.add(lastNameField);
        
        frame.add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        frame.add(firstNameField);
        
        frame.add(new JLabel("Street:"));
        streetField = new JTextField();
        frame.add(streetField);
        
        frame.add(new JLabel("City:"));
        cityField = new JTextField();
        frame.add(cityField);
        
        frame.add(new JLabel("State:"));
        String[] states = {"IL", "MO", "KS", "MD", "FL", "CA", "VA", "TX"};
        stateDropdown = new JComboBox<>(states);
        frame.add(stateDropdown);
        
        frame.add(new JLabel("ZIP:"));
        zipField = new JTextField();
        frame.add(zipField);
        
        frame.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        frame.add(phoneField);
        
        frame.add(new JLabel("Account Type:"));
        JPanel accountPanel = new JPanel();
        accountPanel.setBackground(new Color(200, 255, 200));
        checkingButton = new JRadioButton("Checking");
        savingsButton = new JRadioButton("Savings");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(checkingButton);
        accountGroup.add(savingsButton);
        accountPanel.add(checkingButton);
        accountPanel.add(savingsButton);
        frame.add(accountPanel);
        
        addCustomerButton = new JButton("Add New Customer and Account");
        frame.add(addCustomerButton);
        
        displayButton = new JButton("Display Customer and Account Data");
        frame.add(displayButton);
        
        frame.add(new JLabel("Transaction Amount:"));
        amountField = new JTextField();
        frame.add(amountField);
        
        frame.add(new JLabel("Transaction Type:"));
        JPanel transactionPanel = new JPanel();
        transactionPanel.setBackground(new Color(200, 255, 200));
        depositButton = new JRadioButton("Deposit");
        withdrawButton = new JRadioButton("Withdraw");
        ButtonGroup transactionGroup = new ButtonGroup();
        transactionGroup.add(depositButton);
        transactionGroup.add(withdrawButton);
        transactionPanel.add(depositButton);
        transactionPanel.add(withdrawButton);
        frame.add(transactionPanel);
        
        transactionButton = new JButton("Perform Transaction");
        frame.add(transactionButton);
        
        clearButton = new JButton("Clear");
        frame.add(clearButton);
        
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        frame.add(new JScrollPane(outputArea));
        
        frame.setVisible(true);
        
        // Action Listeners
        addCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCustomer();
            }
        });
        
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayCustomer();
            }
        });
        
        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performTransaction();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
    }
    
    private void addCustomer() {
        String id = idField.getText();
        String ssn = ssnField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String street = streetField.getText();
        String city = cityField.getText();
        String state = (String) stateDropdown.getSelectedItem();
        String zip = zipField.getText();
        String phone = phoneField.getText();
        
        Customer customer = new Customer(id, ssn, lastName, firstName, street, city, state, zip, phone);
        if (checkingButton.isSelected()) {
            customer.setAccount(new CheckingAccount(id));
        } else if (savingsButton.isSelected()) {
            customer.setAccount(new SavingsAccount(id));
        }
        customers.add(customer);
        outputArea.setText("Customer and account successfully added.");
    }
    
    private void displayCustomer() {
        outputArea.setText("");
        for (Customer c : customers) {
            outputArea.append(c.toString() + "\n");
        }
    }
    
    private void performTransaction() {
        double amount = Double.parseDouble(amountField.getText());
        for (Customer c : customers) {
            Account account = c.getAccount();
            if (depositButton.isSelected()) {
                account.deposit(amount);
            } else if (withdrawButton.isSelected()) {
                account.withdrawal(amount);
                if (account instanceof CheckingAccount && account.getBalance() < 0) {
                    outputArea.setText("Insufficient funds, fee charged");
                }
            }
            outputArea.append("Transaction complete. Balance: $" + account.getBalance() + "\n");
        }
    }
    
    private void clearFields() {
        idField.setText(""); ssnField.setText(""); lastNameField.setText("");
        firstNameField.setText(""); streetField.setText(""); cityField.setText("");
        zipField.setText(""); phoneField.setText(""); amountField.setText("");
    }
    
    public static void main(String[] args) {
        new BankAppGUI();
    }
}
