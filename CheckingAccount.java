public class CheckingAccount extends Account {
    private static final double SERVICE_FEE = 0.50; // 50 cents
    private static final double OVERDRAFT_FEE = 30.00; // $30 overdraft fee
    private static final double INTEREST_RATE = 0.02; // 2% interest rate

    public CheckingAccount(String accountNumber) {
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
        double newBalance = getBalance() - amount - getServiceFee();
        if (newBalance < 0) {
            newBalance -= OVERDRAFT_FEE; // Apply overdraft fee if balance is below zero
            System.out.println("Warning: Overdraft fee applied!");
        }
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
        System.out.println("Interest applied at 2% rate.");
    }
}
