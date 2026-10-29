import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        while (true) {
            System.out.println("1. Add Account");
            System.out.println("2. Show Count");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    bank.addAccount(sc.nextLine());
                    System.out.println("Account added");
                    break;

                case 2:
                    System.out.println("Total accounts: " + bank.getCount());
                    break;

                case 3:
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid action");
            }
        }
    }
}