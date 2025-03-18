import java.time.LocalDate;

public class SavingsAccount extends Account {
    private static final double TRANSACTION_FEE = 0.25;

    public SavingsAccount(String accountNumber) {
        super(accountNumber, "SAV", TRANSACTION_FEE, 5.0); // 5% interest
    }

    @Override
    public void withdrawal(double amount) {
        if (amount <= 0) {
            System.out.println("Error: Withdrawal amount must be greater than zero.");
            return;
        }

        if (balance - (amount + TRANSACTION_FEE) < 0) {
            System.out.println("Error: Insufficient funds. Savings accounts cannot go negative.");
            return;
        }

        balance -= (amount + TRANSACTION_FEE);
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
        return balance * 1.05; // Apply 5% interest
    }
}
