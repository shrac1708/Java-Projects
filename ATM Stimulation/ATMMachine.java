import java.util.ArrayList;
import java.util.Scanner;

public class ATMMachine {

    private double balance;
    private String pin;
    private ArrayList<String> transactionHistory;

    public ATMMachine(String initialPin) {
        this.balance = 0.0;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    // Method to display the account balance
    public void checkBalance() {
        System.out.println("Your current balance is: $" + balance);
        transactionHistory.add("Checked balance: $" + balance);
    }

    // Method to deposit cash
    public void depositCash(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
            transactionHistory.add("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw cash
    public void withdrawCash(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
            transactionHistory.add("Withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    // Method to change the PIN
    public void changePin(String oldPin, String newPin) {
        if (this.pin.equals(oldPin)) {
            this.pin = newPin;
            System.out.println("PIN successfully changed.");
            transactionHistory.add("PIN changed.");
        } else {
            System.out.println("Incorrect old PIN.");
        }
    }

    // Method to display transaction history
    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }

    // Main program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMMachine atm = new ATMMachine("1234");

        System.out.println("Welcome to the ATM Machine!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.checkBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.depositCash(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawalAmount = scanner.nextDouble();
                    atm.withdrawCash(withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter old PIN: ");
                    String oldPin = scanner.next();
                    System.out.print("Enter new PIN: ");
                    String newPin = scanner.next();
                    atm.changePin(oldPin, newPin);
                    break;
                case 5:
                    atm.showTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
