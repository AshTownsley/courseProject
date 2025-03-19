package courseProject;

public class SavingsAccount extends Account {
    private static final double SERVICE_FEE = 0.25; // 25 cents
    private static final double INTEREST_RATE = 0.05; // 5% interest rate

    public SavingsAccount(String accountNumber) {
        super(accountNumber);
        setServiceFee(SERVICE_FEE);
        setInterestRate(INTEREST_RATE);
    }

    @Override
    public void deposit(double amount) {
        double newBalance = getBalance() + amount - getServiceFee();
        setBalance(newBalance);
        System.out.println("Deposited: $" + amount + " (Service Fee: $" + getServiceFee() + ")");
    }

    @Override
    public void withdrawal(double amount) {
        if (amount > getBalance()) {
            System.out.println("Error: Cannot withdraw more than available balance.");
            return; // Deny withdrawal if it exceeds balance
        }
        double newBalance = getBalance() - amount - getServiceFee();
        setBalance(newBalance);
        System.out.println("Withdrew: $" + amount + " (Service Fee: $" + getServiceFee() + ")");
    }

    @Override
    public double balance() {
        return getBalance();
    }

    @Override
    public void applyInterest() {
        double interest = getBalance() * getInterestRate();
        setBalance(getBalance() + interest);
        System.out.println("Interest applied at 5% rate.");
    }
}
