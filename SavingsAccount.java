package courseProject;

public class SavingsAccount extends Account {
    private static final double SERVICE_FEE = 0.25;
    private static final double INTEREST_RATE = 0.05; // 5% interest rate

    public SavingsAccount(String customerID) {
        super(customerID);
    }

    @Override
    public void deposit(double amount) {
        balance += amount - SERVICE_FEE; // Subtract service fee
    }

    @Override
    public void withdrawal(double amount) {
        if (balance < amount) {
            System.out.println("Error: Insufficient funds. Withdrawal denied.");
        } else {
            balance -= amount + SERVICE_FEE; // Subtract withdrawal and service fee
        }
    }

    @Override
    public void applyInterest() {
        balance *= (1 + INTEREST_RATE); // Apply 5% interest
    }
}
