import java.util.Scanner;

public class Atm {

    private static User validateUser(String uuid, String pin, Bank bank) {
        return bank.userLogin(uuid,pin);
    }
    private static void userMainMenu(User user, Scanner sc) {

        user.printAccountsSummary();

        int choice;
        do {
            System.out.println();
            System.out.printf(" Welcome %s !! What would you like to do today.", user.getName());
            System.out.println();
            System.out.println(" 1) Show account transaction history");
            System.out.println(" 2) Deposit");
            System.out.println(" 3) Withdraw");
            System.out.println(" 4) Transfer to another account");
            System.out.println(" 5) Quit");
            System.out.println();
            System.out.println("Enter your choice : ");
            choice = sc.nextInt();
            if(choice > 5 || choice < 1) {
                System.out.println("Invalid choice provided please provide 1-5");
            } else {
                switch (choice) {
                    case 1:
                        Atm.showAccountHistory(user, sc);
                        break;
                    case 2:
                        Atm.depositMoney(user, sc);
                        break;
                    case 3:
                        Atm.withdrawMoney(user, sc);
                    case 4:
                        Atm.transferMoney(user, sc);
                    default:
                        System.out.println("Quitting");
                }
            }
        } while (choice != 5);

    }

    private static void transferMoney(User user, Scanner sc) {
    }

    private static void withdrawMoney(User user, Scanner sc) {
    }

    private static void depositMoney(User user, Scanner sc) {
    }

    private static void showAccountHistory(User user, Scanner sc) {

        int accountNumber;

        System.out.println("Enter your account number");
        accountNumber = sc.nextInt();
        user.getAccountHistory(accountNumber);


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank sbi = new Bank("Stata Bank Of India");
        // Create a user and create a saving account for him.
        User userAditya = sbi.addUser("Aditya","Anand", "ayushi");
        // Create a current account for user.
        Account currentAccount = new Account("Current", userAditya, sbi);
        userAditya.addAccount(currentAccount);
        sbi.addAccount(currentAccount);

        // Provide a login prompt for user.
        while(true) {
            // Ask for uuid and pin.
            System.out.println();
            System.out.printf("Welcome to %s", sbi.getName());
            System.out.println();
            System.out.println("Please provide your uuid : ");
            String uuid = sc.nextLine();
            System.out.println("Please provide your pin : ");
            String pin = sc.nextLine();
            User currentUser = Atm.validateUser(uuid,pin, sbi);
            if(currentUser != null) {
                // Give user a main menu.
                Atm.userMainMenu(currentUser, sc);
            } else {
                System.out.println("Invalid uuid and pin, please try again");
            }
        }
    }
}
