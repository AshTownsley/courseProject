import java.time.LocalDate;

public class CheckingAccount extends Account {
    private static final double OVERDRAFT_FEE = 30.00;
    private static final double TRANSACTION_FEE = 0.50;

    public CheckingAccount(String accountNumber) {
        super(accountNumber, "CHK", TRANSACTION_FEE, 2.0); // 2% interest
    }

    @Override
    public void withdrawal(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return;
        }

        balance -= (amount + TRANSACTION_FEE);
        if (balance < 0) {
            balance -= OVERDRAFT_FEE;
            System.out.println("Warning: Overdraft fee of $30 applied.");
        }

        System.out.println("Withdrawal successful. New balance: $" + balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Deposit amount must be greater than zero.");
            return;
        }

        balance += (amount - TRANSACTION_FEE);
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    @Override
    public double balance() {
        return balance * 1.02; // Apply 2% interest
    }
}
