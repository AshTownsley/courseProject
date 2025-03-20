import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankAppGUI {
    private JFrame frame;
    private JTextField txtCustomerID, txtFirstName, txtLastName, txtSSN, txtStreet, txtCity, txtZip, txtPhone, txtBalance, txtTransactionAmount;
    private JComboBox<String> cmbState;
    private JRadioButton rdoChecking, rdoSavings, rdoDeposit, rdoWithdraw;
    private JButton btnAddCustomer, btnDisplayCustomer, btnPerformTransaction, btnClear;
    private JLabel lblStatus;

    public BankAppGUI() {
        frame = new JFrame("Bank Account Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new GridLayout(12, 2));

        // Customer Input Fields
        frame.add(new JLabel("Customer ID:"));
        txtCustomerID = new JTextField();
        frame.add(txtCustomerID);
        
        frame.add(new JLabel("First Name:"));
        txtFirstName = new JTextField();
        frame.add(txtFirstName);
        
        frame.add(new JLabel("Last Name:"));
        txtLastName = new JTextField();
        frame.add(txtLastName);
        
        frame.add(new JLabel("SSN:"));
        txtSSN = new JTextField();
        frame.add(txtSSN);
        
        frame.add(new JLabel("Street:"));
        txtStreet = new JTextField();
        frame.add(txtStreet);
        
        frame.add(new JLabel("City:"));
        txtCity = new JTextField();
        frame.add(txtCity);
        
        frame.add(new JLabel("State:"));
        cmbState = new JComboBox<>(new String[]{"IL", "MO", "KS", "MD", "FL", "CA", "VA", "TX"});
        frame.add(cmbState);
        
        frame.add(new JLabel("ZIP:"));
        txtZip = new JTextField();
        frame.add(txtZip);
        
        frame.add(new JLabel("Phone:"));
        txtPhone = new JTextField();
        frame.add(txtPhone);
        
        // Account Type Selection
        frame.add(new JLabel("Account Type:"));
        JPanel accountPanel = new JPanel();
        rdoChecking = new JRadioButton("Checking");
        rdoSavings = new JRadioButton("Savings");
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(rdoChecking);
        accountGroup.add(rdoSavings);
        accountPanel.add(rdoChecking);
        accountPanel.add(rdoSavings);
        frame.add(accountPanel);
        
        // Buttons
        btnAddCustomer = new JButton("Add New Customer and Account");
        frame.add(btnAddCustomer);
        
        btnDisplayCustomer = new JButton("Display Customer and Account Data");
        frame.add(btnDisplayCustomer);
        
        btnPerformTransaction = new JButton("Perform Transaction");
        frame.add(btnPerformTransaction);
        
        btnClear = new JButton("Clear");
        frame.add(btnClear);
        
        // Status Label
        lblStatus = new JLabel("Status: ");
        frame.add(lblStatus);

        // Add Action Listeners (To Be Implemented)
        btnAddCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lblStatus.setText("Customer added successfully!");
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        frame.setVisible(true);
    }

    private void clearFields() {
        txtCustomerID.setText("");
        txtFirstName.setText("");
        txtLastName.setText("");
        txtSSN.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtZip.setText("");
        txtPhone.setText("");
        rdoChecking.setSelected(false);
        rdoSavings.setSelected(false);
        lblStatus.setText("Status: ");
    }

    public static void main(String[] args) {
        new BankAppGUI();
    }
}
