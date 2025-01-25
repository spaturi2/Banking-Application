import java.util.Scanner;

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private long balance;

    public void openAccount(Scanner scanner) {
        System.out.print("Enter Account Number: ");
        accountNumber = scanner.next();
        System.out.print("Enter Account Type: ");
        accountType = scanner.next();
        System.out.print("Enter Account Holder Name: ");
        accountHolderName = scanner.next();
        System.out.print("Enter Balance: ");
        balance = scanner.nextLong();
    }

    public void displayAccountInfo() {
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: " + balance);
    }

    public void deposit(Scanner scanner) {
        System.out.print("Enter the amount you want to deposit: ");
        long amount = scanner.nextLong();
        balance += amount;
        System.out.println("Deposit successful. Balance after deposit: " + balance);
    }

    public void withdraw(Scanner scanner) {
        System.out.print("Enter the amount you want to withdraw: ");
        long amount = scanner.nextLong();
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal successful. Balance after withdrawal: " + balance);
        } else {
            System.out.println("Insufficient balance. Withdrawal failed.");
        }
    }

    public boolean search(String accountNo) {
        return accountNumber.equals(accountNo);
    }
}

public class BankingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("How many customers do you want to input? ");
        int numberOfCustomers = scanner.nextInt();
        BankAccount[] accounts = new BankAccount[numberOfCustomers];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new BankAccount();
            accounts[i].openAccount(scanner);
        }

        int choice;
        do {
            System.out.println("\n*** Banking System Application ***");
            System.out.println("1. Display all account details");
            System.out.println("2. Search by Account number");
            System.out.println("3. Deposit the amount");
            System.out.println("4. Withdraw the amount");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    for (BankAccount account : accounts) {
                        account.displayAccountInfo();
                    }
                    break;
                case 2:
                    System.out.print("Enter account number to search: ");
                    String accountNo = scanner.next();
                    boolean found = false;
                    for (BankAccount account : accounts) {
                        if (account.search(accountNo)) {
                            account.displayAccountInfo();
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter account number to deposit: ");
                    accountNo = scanner.next();
                    found = false;
                    for (BankAccount account : accounts) {
                        if (account.search(accountNo)) {
                            account.deposit(scanner);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number to withdraw: ");
                    accountNo = scanner.next();
                    found = false;
                    for (BankAccount account : accounts) {
                        if (account.search(accountNo)) {
                            account.withdraw(scanner);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("Account not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
