package courseProject;

public class CheckingAccount extends Account {
    private static final double SERVICE_FEE = 0.50;
    private static final double OVERDRAFT_FEE = 30.00;
    private static final double INTEREST_RATE = 0.02; // 2% interest rate

    public CheckingAccount(String customerID) {
        super(customerID);
    }

    @Override
    public void deposit(double amount) {
        balance += amount - SERVICE_FEE; // Subtract service fee
    }

    @Override
    public void withdrawal(double amount) {
        if (balance - amount < 0) {
            balance -= OVERDRAFT_FEE; // Charge overdraft fee if balance goes below zero
        } else {
            balance -= amount + SERVICE_FEE; // Subtract withdrawal and service fee
        }
    }

    @Override
    public double getBalance() {
        return balance; // No interest is applied automatically here
    }

    @Override
    public void applyInterest() {
        balance *= (1 + INTEREST_RATE); // Apply 2% interest
    }
}
