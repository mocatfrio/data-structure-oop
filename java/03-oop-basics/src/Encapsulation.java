class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdraw: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance");
        }
    }

    public void displayInfo() {
        System.out.println("Account: " + accountNumber);
        System.out.println("Owner: " + ownerName);
        System.out.println("Balance: " + balance);
    }
}

public class Encapsulation {
    public static void main(String[] args) {
        System.out.println("=== ENCAPSULATION ===\n");

        BankAccount account = new BankAccount("1234567890", "John Doe", 1000.0);
        account.displayInfo();

        System.out.println();
        account.deposit(500);
        account.withdraw(200);
        account.withdraw(2000);

        System.out.println();
        account.displayInfo();
    }
}
