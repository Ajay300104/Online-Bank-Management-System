package BankManage;
import java.util.Scanner;  // Import the Scanner class

public class BankManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            bank.displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter username: ");
                    String username = scanner.next();
                    System.out.println("Enter password: ");
                    String password = scanner.next();
                    System.out.println("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.println("Enter account holder name: ");
                    String accountHolderName = scanner.next();
                    System.out.println("Enter initial balance: ");
                    double initialBalance = scanner.nextDouble();
                    bank.createAccount(username, password, accountNumber, accountHolderName, initialBalance);
                    System.out.println("Account created successfully.");
                    break;
                case 2:
                    System.out.println("Enter username: ");
                    String loginUsername = scanner.next();
                    System.out.println("Enter password: ");
                    String loginPassword = scanner.next();
                    User user = bank.login(loginUsername, loginPassword);
                    if (user != null) {
                        bank.accountMenu(user);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
