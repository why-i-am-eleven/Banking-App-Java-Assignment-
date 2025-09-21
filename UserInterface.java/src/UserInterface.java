import java.util.Scanner;

/**
 * Account class represents a bank account with basic operations.
 */
class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive!");
        } else if (balance >= amount) {
            balance -= amount;
            System.out.println("Successfully withdrawn: " + amount);
        } else {
            System.out.println("Insufficient balance!");
        }
    }

    public void displayAccountDetails() {
        System.out.println("\n----- Account Details -----");
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Holder Name      : " + accountHolderName);
        System.out.println("Balance          : " + balance);
        System.out.println("Email            : " + email);
        System.out.println("Phone Number     : " + phoneNumber);
        System.out.println("---------------------------");
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully!");
    }
}

/**
 * UserInterface class handles account operations using an array.
 */
public class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;

    public UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    public void createAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Bank account limit reached! Cannot create more accounts.");
            return;
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Initial Balance: ");
        double balance = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        int accountNumber = 1000 + accountCount; // Unique account number
        accounts[accountCount] = new Account(accountNumber, name, balance, email, phone);
        System.out.println("Account created successfully! Your Account Number is: " + accountNumber);

        accountCount++;
    }

    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void performDeposit() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter Amount to Deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter Amount to Withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void showAccountDetails() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    public void updateContact() {
        System.out.print("Enter Account Number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter New Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter New Phone Number: ");
            String phone = scanner.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n==== Welcome to the Banking Application! ====");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    showAccountDetails();
                    break;
                case 5:
                    updateContact();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Application!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    public static void main(String[] args) {
        UserInterface bankApp = new UserInterface(100); // max 100 accounts
        bankApp.mainMenu();
    }
}
