import java.util.ArrayList;
import java.util.List;

public class Account {

    /*
    * Type of account saving, checking or current.
    */
    private String type;
    private String accountNumber;
    private User accountHolder;
    private List<Transaction> transactions;

    public Account(String type, User accountHolder, Bank bank) {
        this.type = type;
        this.accountHolder = accountHolder;
        this.accountNumber = bank.getNewAccountNumber();
        // initialize transactions.
        this.transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public String getSummaryLine() {

        // Get balance.
        double balance = this.getBalance();
        // Create summary line.
        if(balance >= 0) {
            return String.format("%s Rs. %.02f %s ", this.accountNumber, balance, accountHolder.getName());
        } else {
            return String.format("%s Rs. %(.02f) %s ", this.accountNumber, balance, accountHolder.getName());
        }

    }

    private double getBalance() {
        double balance = 0;
        for(Transaction transaction : this.transactions) {
            balance += transaction.getAmount();
        }
        return balance;
    }

    public void printAccountSummary(int accountNumber) {
        System.out.printf("Transaction history for account %d ", accountNumber);
        // Print the latest transactions.
        for(int i = this.transactions.size()-1; i>=0;i--) {
            System.out.println(this.transactions.get(i).getTransactionSummary());
        }
    }
}
