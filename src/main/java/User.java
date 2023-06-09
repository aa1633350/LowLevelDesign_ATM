import java.util.ArrayList;
import java.util.List;

public class User {

    private String firstName;
    private String lastName;
    // uuid needs to be generated by system and should be random.
    private String uuid;
    private String password;
    private List<Account> accountList;


    /*
    Creates a new user.
     */
    public User(String firstName, String lastName, String password, Bank bank) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;

        // get a unique UID for the user.
        this.uuid = bank.getUserID();

        // for each user create an empty list of accounts.
        accountList = new ArrayList<>();
        System.out.printf("New user %s created with user id %s for bank %s", firstName , uuid, bank.getName());
    }


    public void addAccount(Account account) {
        this.accountList.add(account);
    }

    public String getUuid() {
        return uuid;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void printAccountsSummary() {
        System.out.printf("\n\n%s's accounts summary", this.getName());
        for (Account account : accountList) {
            account.getSummaryLine();
        }
    }

    public void getAccountHistory(int accountNumber) {

        for(Account account : accountList) {
            if(account.getAccountNumber().equals(String.valueOf(accountNumber))) {
                account.printAccountSummary(accountNumber);
                return;
            }
        }
    }
}
