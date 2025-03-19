package courseProject;

public abstract class Account {
    protected double balance; // Make balance protected if using in subclasses
    private String customerID;

    public Account(String customerID) {
        this.customerID = customerID;
        this.balance = 0.0; // Initial balance set to 0
    }

    public String getAccountType() {
        return this.getClass().getSimpleName();
    }

    // Getter method to access balance
    public double getBalance() {
        return balance;
    }

    // Abstract methods for subclasses to implement
    public abstract void deposit(double amount);
    public abstract void withdrawal(double amount);
    public abstract void applyInterest();
}
