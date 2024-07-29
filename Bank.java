package BankManage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Bank {
    private Map<String, User> users;

    public Bank() {
        users = new HashMap<>();
    }

    public void createAccount(String username, String password, String accountNumber, String accountHolderName, double initialBalance) {
        Account account = new Account(accountNumber, accountHolderName, initialBalance);
        User user = new User(username, password, account);
        users.put(username, user);
    }

    public User login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    public void displayMenu() {
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public void accountMenu(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Logout");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Balance: " + user.getAccount().getBalance());
                    break;
                case 2:
                    System.out.println("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    user.getAccount().deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (user.getAccount().withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Enter recipient account number: ");
                    String toAccountNumber = scanner.next();
                    System.out.println("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    Account toAccount = getAccountByNumber(toAccountNumber);
                    if (toAccount != null) {
                        if (user.getAccount().transfer(toAccount, transferAmount)) {
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Transfer failed.");
                        }
                    } else {
                        System.out.println("Recipient account not found.");
                    }
                    break;
                case 5:
                    System.out.println("Transaction History: ");
                    for (Transaction transaction : user.getAccount().getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    return;
            }
        }
    }

    private Account getAccountByNumber(String accountNumber) {
        for (User user : users.values()) {
            if (user.getAccount().getAccountNumber().equals(accountNumber)) {
                return user.getAccount();
            }
        }
        return null;
    }
}

