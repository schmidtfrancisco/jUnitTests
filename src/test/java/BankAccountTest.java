import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BankAccountTest {

    private BankAccount bankAccount;

    @BeforeEach
    public void setup() {
        bankAccount = new BankAccount("01012132", 20000);
    }

    @Test
    public void validDepositTest() {
        double actualBalance = bankAccount.getBalance();
        bankAccount.deposit(24500);
        double expectedBalance = actualBalance + 24500;
        assertEquals(expectedBalance, bankAccount.getBalance());
    }
    @Test
    public void validDecimalsDepositTest() {
        double actualBalance = bankAccount.getBalance();
        bankAccount.deposit(12456.59);
        double expectedBalance = actualBalance + 12456.59;
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    public void emptyDepositTest() {
        double actualBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(0));
        assertEquals(actualBalance, bankAccount.getBalance());
    }

    @Test
    public void negativeDepositTest() {
        double actualBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-4523.23));
        assertEquals(actualBalance, bankAccount.getBalance());
    }

    @Test
    public void validWithdrawTest() {
        double actualBalance = bankAccount.getBalance();
        bankAccount.withdraw(100);
        double expectedBalance = actualBalance - 100;
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    public void validDecimalsWithdrawTest() {
        double actualBalance = bankAccount.getBalance();
        bankAccount.withdraw(4523.63);
        double expectedBalance = actualBalance - 4523.63;
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    public void withdrawEqualToBalanceTest() {
        double actualBalance = bankAccount.getBalance();
        bankAccount.withdraw(actualBalance);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    public void withdrawGreaterThanBalance() {
        double actualBalance = bankAccount.getBalance();
        double withdrawAmount = actualBalance + 541.75;
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(withdrawAmount));
        assertEquals(actualBalance, bankAccount.getBalance());
    }

    @Test
    public void invalidWithdrawTest() {
        double actualBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-456.96));
        assertEquals(actualBalance, bankAccount.getBalance());
    }

    @Test
    public void zeroWithdrawTest() {
        double actualBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(0));
        assertEquals(actualBalance, bankAccount.getBalance());
    }

    @Test
    public void validTransferTest() {
        BankAccount targetAccount = new BankAccount("4512314", 15000);
        double sourceAccountBalance = bankAccount.getBalance();
        bankAccount.transferTo(targetAccount, 5200);
        double expectedSourceAccountBalance = sourceAccountBalance - 5200;
        assertEquals(expectedSourceAccountBalance, bankAccount.getBalance());
        assertEquals(20200, targetAccount.getBalance());
    }

    @Test
    public void validDecimalsTransferTest() {
        BankAccount targetAccount = new BankAccount("4512314", 15000);
        double sourceAccountBalance = bankAccount.getBalance();
        bankAccount.transferTo(targetAccount, 7456.32);
        double expectedSourceAccountBalance = sourceAccountBalance - 7456.32;
        assertEquals(expectedSourceAccountBalance, bankAccount.getBalance());
        assertEquals(22456.32, targetAccount.getBalance());
    }

    @Test
    public void invalidTargetAccountTest() {
        BankAccount targetAccount = null;
        double actualBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transferTo(targetAccount, 3000));
        assertEquals(actualBalance, bankAccount.getBalance());
    }

    @Test
    public void transferGreaterThanBalanceTest() {
        BankAccount targetAccount = new BankAccount("4512314", 15000);
        double sourceAccountBalance = bankAccount.getBalance();
        double amountToTransfer = sourceAccountBalance + 4320;
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transferTo(targetAccount, amountToTransfer));
        assertEquals(15000, targetAccount.getBalance());
        assertEquals(sourceAccountBalance, bankAccount.getBalance());
    }

    @Test
    public void transferEqualToBalanceTest() {
        BankAccount targetAccount = new BankAccount("4512314", 15000);
        double sourceAccountBalance = bankAccount.getBalance();
        bankAccount.transferTo(targetAccount, sourceAccountBalance);
        double expectedTargetAccountBalance = 15000 + sourceAccountBalance;
        assertEquals(0, bankAccount.getBalance());
        assertEquals(expectedTargetAccountBalance, targetAccount.getBalance());
    }

    @Test
    public void invalidTransferTest() {
        BankAccount targetAccount = new BankAccount("4512314", 15000);
        double sourceAccountBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transferTo(targetAccount, -4521.30));
        assertEquals(15000, targetAccount.getBalance());
        assertEquals(sourceAccountBalance, bankAccount.getBalance());

    }

    @Test
    public void zeroTransferTest() {
        BankAccount targetAccount = new BankAccount("4512314", 15000);
        double sourceAccountBalance = bankAccount.getBalance();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transferTo(targetAccount, 0));
        assertEquals(15000, targetAccount.getBalance());
        assertEquals(sourceAccountBalance, bankAccount.getBalance());

    }

    @Test
    public void invalidBankAccountCreationTest() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("123456", -100.50));
    }

    @Test
    public void gettersTest() {
        BankAccount test = new BankAccount("142536", 1235.85);
        assertEquals("142536", test.getAccountNumber());
        assertEquals(1235.85, test.getBalance());
    }
}
