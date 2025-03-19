public abstract class Account {
    private String accountNumber;
    private double balance;
    private double serviceFee;
    private double interestRate;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0.0; // Initial balance is 0
    }

    public abstract void deposit(double amount);
    public abstract void withdrawal(double amount);
    public abstract double balance();

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public abstract void applyInterest();  // Specific to checking or savings accounts
}
