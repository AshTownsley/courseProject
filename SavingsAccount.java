import java.time.LocalDate;

public class SavingsAccount extends Account {
    private static final double SERVICE_FEE = 0.25;
    private static final double INTEREST_RATE = 0.05;

    public SavingsAccount(String accountNumber) {
        super(accountNumber, "Savings");
    }

    @Override
    public void withdrawal(double amount) {
        if (amount + SERVICE_FEE > getBalance()) {
            System.out.println("Insufficient funds! Savings account cannot have a negative balance.");
        } else {
            setBalance(getBalance() - amount - SERVICE_FEE);
            System.out.println("Withdrawal successful. Service fee of $0.25 applied.");
        }
        displayBalance();
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount - SERVICE_FEE);
        System.out.println("Deposit successful. Service fee of $0.25 applied.");
        displayBalance();
    }

    public void applyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        setBalance(getBalance() + interest);
        System.out.println("Interest applied at 5% rate.");
    }
}
