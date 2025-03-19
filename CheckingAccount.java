import java.time.LocalDate;

public class CheckingAccount extends Account {
    private static final double SERVICE_FEE = 0.50;
    private static final double OVERDRAFT_FEE = 30.00;
    private static final double INTEREST_RATE = 0.02;

    public CheckingAccount(String accountNumber) {
        super(accountNumber, "Checking");
    }

    @Override
    public void withdrawal(double amount) {
        if (amount + SERVICE_FEE <= getBalance()) {
            setBalance(getBalance() - amount - SERVICE_FEE);
            System.out.println("Withdrawal successful. Service fee of $0.50 applied.");
        } else {
            setBalance(getBalance() - amount - SERVICE_FEE - OVERDRAFT_FEE);
            System.out.println("Overdraft! $30 fee applied.");
        }
        displayBalance();
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount - SERVICE_FEE);
        System.out.println("Deposit successful. Service fee of $0.50 applied.");
        displayBalance();
    }

    public void applyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        setBalance(getBalance() + interest);
        System.out.println("Interest applied at 2% rate.");
    }
}
