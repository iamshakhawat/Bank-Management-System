package bank.management.system;

import java.io.*;
import java.util.*;

class BankSystem {

    private static final String ACCOUNTS_FILE = "accounts.txt";
    private static final String TRANSACTIONS_FILE = "transactions.txt";

    private Map<String, SavingsAccount> accounts = new HashMap<>();

    public BankSystem() {
        loadAccounts();
    }

    public void createAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account number already exists!");
        } else {
            SavingsAccount newAccount = new SavingsAccount(accountNumber, name, password);
            accounts.put(accountNumber, newAccount);
            saveAccounts();
            System.out.println("Account created successfully.");
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        SavingsAccount account = accounts.get(accountNumber);
        if (account != null && account.authenticate(password)) {
            System.out.println("Login successful. Welcome, " + account.getName() + "!");
            accountOperations(account);
        } else {
            System.out.println("Invalid account number or password.");
        }
    }

    private void accountOperations(SavingsAccount account) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Account Operations ===");
            System.out.println("[1]. Check Balance");
            System.out.println("[2]. Deposit");
            System.out.println("[3]. Withdraw");
            System.out.println("[4]. Transaction History");
            System.out.println("[5]. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    saveTransaction(account.getAccountNumber(), "Deposit", depositAmount);
                    saveAccounts();
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    account.withdraw(withdrawalAmount);
                    saveTransaction(account.getAccountNumber(), "Withdraw", withdrawalAmount);
                    saveAccounts();
                    break;
                case 4:
                    showTransactionHistory(account.getAccountNumber());
                    break;
                case 5:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void saveAccounts() {
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNTS_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    private void loadAccounts() {
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNTS_FILE))) {
            accounts = (Map<String, SavingsAccount>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No accounts found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    private void saveTransaction(String accountNumber, String type, double amount) {
        SavingsAccount account = accounts.get(accountNumber);
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
                writer.write(accountNumber + " | " + type + " | $" + amount + " | " + new Date());
                writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    private void showTransactionHistory(String accountNumber) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            System.out.println("--- Transaction History ---");
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(accountNumber)) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading transaction history: " + e.getMessage());
        }
    }
}
