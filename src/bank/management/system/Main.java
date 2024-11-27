package bank.management.system;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        BankSystem bankSystem = new BankSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bank Account Management System ---");
            System.out.println("[1]. Create Account");
            System.out.println("[2]. Login");
            System.out.println("[3]. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    bankSystem.createAccount();
                    break;
                case 2:
                    bankSystem.login();
                    break;
                case 3:
                    System.out.println("Exiting system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}