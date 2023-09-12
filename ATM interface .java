import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMInterface {
    private static Map<Integer, Double> accounts = new HashMap<>();
    private static int currentAccountNumber = 1001;
    private static int nextAccountNumber = 1002;
    private static int loggedInAccount = -1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to the ATM");
            System.out.println("1. Log In");
            System.out.println("2. Create an Account");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void login(Scanner scanner) {
        System.out.print("Enter your account number: ");
        int accountNumber = scanner.nextInt();

        if (accounts.containsKey(accountNumber)) {
            System.out.print("Enter your PIN: ");
            int pin = scanner.nextInt();

            if (pin == accountNumber % 1000) {
                loggedInAccount = accountNumber;
                showLoggedInMenu(scanner);
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
        } else {
            System.out.println("Account not found. Please create an account.");
        }
    }

    private static void showLoggedInMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nLogged In as Account " + loggedInAccount);
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Log Out");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    loggedInAccount = -1;
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void checkBalance() {
        double balance = accounts.get(loggedInAccount);
        System.out.println("Account " + loggedInAccount + " balance: $" + balance);
    }

    private static void deposit(Scanner scanner) {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            double currentBalance = accounts.get(loggedInAccount);
            accounts.put(loggedInAccount, currentBalance + amount);
            System.out.println("$" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void withdraw(Scanner scanner) {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();

        double currentBalance = accounts.get(loggedInAccount);

        if (amount > 0 && amount <= currentBalance) {
            accounts.put(loggedInAccount, currentBalance - amount);
            System.out.println("$" + amount + " withdrawn successfully.");
        } else if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    private static void createAccount(Scanner scanner) {
        int newAccountNumber = nextAccountNumber++;
        int newPin = newAccountNumber % 1000;
        double initialBalance = 0.0;

        accounts.put(newAccountNumber, initialBalance);

        System.out.println("Account created successfully!");
        System.out.println("Your Account Number: " + newAccountNumber);
        System.out.println("Your PIN: " + newPin);
    }
}
