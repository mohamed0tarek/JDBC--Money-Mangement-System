package view;

import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import model.Client;
import operations.Deposite;
import operations.Transfer;
import operations.Withdraw;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientAuthority {

    private static Scanner scanner = new Scanner(System.in);

    public static void blankWindow() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static boolean transferMoneyToAccount(int senderID) throws SQLException {
        blankWindow();
        System.out.println("Please enter Account id and amount of money to Transfer.. ");
        System.out.print("ID : "); int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Amount : "); double amount = scanner.nextDouble();
        Client client = new ClientDaoImpl().searchClient(id);
        if(client != null){
            Transfer.executeTransfer(senderID, id, amount);
        } else {
            System.err.println("The entered ID not exists ");
            return false;
        }
        return true;
    }

    public static boolean depositMoney(int id) throws SQLException {
        blankWindow();
        System.out.println("Please enter amount of money to deposit");
        System.out.println("or enter 0 to exit\n");
        boolean success = false;
        do {
            System.out.print("Amount : "); double amount = scanner.nextDouble();
            if(amount == 0) return true;
            success = Deposite.addToBalance(id, amount);
        }while (!success);
        return true;
    }

    public static boolean withdrawMoney(int id) throws SQLException {
        blankWindow();
        System.out.println("Please enter amount of money to withdraw");
        System.out.println("or enter 0 to exit\n");
        boolean success = false;
        do{
            System.out.print("Amount : "); double amount = scanner.nextDouble();
            if (amount == 0) return true;
            success = Withdraw.removeFromBalance(id, amount);
        }while (!success);
        return true;
    }

    public static boolean showMyHistory(int id) throws SQLException {
        blankWindow();
        System.out.println("Your History");
        System.out.println("---------------------");
        new HistoryDaoImpl().getClientHistory(id);
        return true;
    }

    public static boolean AccountInformation(int id) throws SQLException {
        blankWindow();
        Client client = new ClientDaoImpl().searchClient(id);
        System.out.println("Account Info.");
        System.out.println("---------------");
        System.out.println(
                "client_id : " + client.getClientId() +
                        "\nfirst_name : " + client.getFirstName() +
                        "\nlast_name : " + client.getLastName() +
                        "\nbalance : " + client.getBalance() + "\n");
        return true;
    }
}
