public abstract class Account implements AccountInterface {
    protected String accountNumber;
    protected String accountType;
    protected double balance;
    protected double serviceFee;
    protected double interestRate;

    public Account(String accountNumber, String accountType, double serviceFee, double interestRate) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.serviceFee = serviceFee;
        this.interestRate = interestRate;
        this.balance = 0.0; // Default initial balance
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getBalance() { return balance; }
    
    // Abstract methods to be implemented in subclasses
    public abstract void withdrawal(double amount);
    public abstract void deposit(double amount);
    public abstract double balance();
}
