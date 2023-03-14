import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {

    private String name;



    private List<User> users;
    private List<Account> accounts;
    private final int UUID_LEN = 6;
    private final int ACC_NUMBER_LEN = 10;

    public Bank(String name) {
        this.name = name;
        this.users = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    /**
     * Generates a new universally unique ID for user.
     * @return UUID for user.
     */
    public String getUserID() {
        StringBuilder userID = new StringBuilder();
        Random random = new Random();
        boolean nonUnique;
        do {
            for(int i=0;i<UUID_LEN;i++) {
                userID.append(random.nextInt(10));
            }
            nonUnique = false;
            for(User user : users) {
                if(user.getUuid().equals(userID.toString())) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return userID.toString();
    }

    public String getNewAccountNumber() {
        StringBuilder accountNumber = new StringBuilder();
        Random random = new Random();
        boolean nonUnique;
        do {
            for(int i=0;i<ACC_NUMBER_LEN;i++) {
                accountNumber.append(random.nextInt(10));
            }
            nonUnique = false;
            for(Account account : accounts) {
                if(account.getAccountNumber().equals(accountNumber.toString())) {
                    nonUnique = true;
                    break;
                }
            }
        } while (nonUnique);

        return accountNumber.toString();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public User addUser(String firstName, String lastName, String password) {
        User newUser = new User(firstName,lastName, password, this);
        this.users.add(newUser);
        // create a saving account for user.
        Account newAccount = new Account("Saving", newUser, this);
        newUser.addAccount(newAccount);
        this.addAccount(newAccount);
        return newUser;
    }

    public User userLogin(String uuid, String passowrd) {

        for(User user : users) {
            if(user.getUuid().equals(uuid)) { // userId matches
                if(user.validatePassword(passowrd)) { // Look for password match.
                    System.out.printf("Welcome user %s to %s ", user.getName(), this.getName());
                    return user;
                }
            }
        }
        return null;
    }
}
