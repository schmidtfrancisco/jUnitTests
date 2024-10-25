public class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid withdrawal amount");
        }
    }

    public void transferTo(BankAccount targetAccount, double amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            targetAccount.deposit(amount);
        } else {
            throw new IllegalArgumentException("Invalid transfer amount");
        }
    }
}
