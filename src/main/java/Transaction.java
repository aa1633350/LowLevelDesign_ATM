import java.util.Date;

public class Transaction {



    private double amount;
    private Date timeStamp;
    private Account inAccount;


    public Transaction(double amount, Account inAccount) {
        this.amount = amount;
        this.timeStamp = new Date();
        this.inAccount = inAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionSummary() {

        return String.format("%s %.02f ", this.timeStamp.toString(), this.amount);

    }
}
