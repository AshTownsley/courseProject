public class Account {
    private String accountNumber;
    private String accountType; // "CHK" or "SAV"
    private double serviceFee;
    private double interestRate;
    private double overdraftFee;
    private double balance;

    public Account(String accountNumber, String accountType, double serviceFee, double interestRate, double overdraftFee) {
        setAccountNumber(accountNumber);
        setAccountType(accountType);
        setServiceFee(serviceFee);
        setInterestRate(interestRate);
        setOverdraftFee(overdraftFee);
        this.balance = 0.0; // Default balance
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountType() { return accountType; }
    public double getServiceFee() { return serviceFee; }
    public double getInterestRate() { return interestRate; }
    public double getOverdraftFee() { return overdraftFee; }
    public double getBalance() { return balance; }

    // Setters with validation
    public void setAccountNumber(String accountNumber) {
        if (accountNumber.length() <= 5 && !accountNumber.isBlank()) {
            this.accountNumber = accountNumber;
        } else {
            throw new IllegalArgumentException("Account Number must be max 5 characters and not blank.");
        }
    }

    public void setAccountType(String accountType) {
        if (accountType.equalsIgnoreCase("CHK") || accountType.equalsIgnoreCase("SAV")) {
            this.accountType = accountType.toUpperCase();
        } else {
            throw new IllegalArgumentException("Account Type must be 'CHK' or 'SAV'.");
        }
    }

    public void setServiceFee(double serviceFee) {
        if (serviceFee >= 0.0 && serviceFee <= 10.0) {
            this.serviceFee = serviceFee;
        } else {
            throw new IllegalArgumentException("Service Fee must be between 0 and 10.00.");
        }
    }

    public void setInterestRate(double interestRate) {
        if (interestRate >= 0.0 && interestRate <= 10.0) {
            this.interestRate = interestRate;
        } else {
            throw new IllegalArgumentException("Interest Rate must be between 0 and 10%.");
        }
    }

    public void setOverdraftFee(double overdraftFee) {
        if (overdraftFee >= 0.0) {
            this.overdraftFee = overdraftFee;
        } else {
            throw new IllegalArgumentException("Overdraft Fee cannot be negative.");
        }
    }

    @Override
    public String toString() {
        return String.format("Account Number: %s | Type: %s | Service Fee: $%.2f | Interest Rate: %.2f%% | Overdraft Fee: $%.2f | Balance: $%.2f",
                accountNumber, accountType, serviceFee, interestRate, overdraftFee, balance);
    }
}
