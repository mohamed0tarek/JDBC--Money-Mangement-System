package view;

import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import model.Client;
import model.Titles;

import java.sql.SQLException;
import java.util.Scanner;

public class AdminAuthority {

    private static Scanner scanner = new Scanner(System.in);

    public static void blankWindow() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static boolean adminAddClient() throws SQLException {
        blankWindow();
        System.out.println("""
                Please enter the info.
                                
                """);
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();
        System.out.print("First Name : ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name : ");
        String lastName = scanner.nextLine();
        System.out.print("Balance : ");
        double balance = scanner.nextDouble();

        return new ClientDaoImpl().addClient(new Client(id, password, firstName, lastName, balance, Titles.client));
    }

    public static boolean adminDeleteClient() throws SQLException {
        blankWindow();
        System.out.println("Please enter the id of client to be deleted ..");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return new ClientDaoImpl().deleteClient(id);
    }

    public static boolean adminShowAllClients() throws SQLException {
        blankWindow();
        System.out.println("The current existing clients");
        System.out.println("------------------------------");
        return new ClientDaoImpl().getAllClients();
    }

    public static boolean adminShowAllHistory() throws SQLException {
        blankWindow();
        System.out.println("History Recorded ");
        System.out.println("---------------------------");
        return new HistoryDaoImpl().getAllHistory();
    }

    public static boolean adminShowClientHistory() throws SQLException {
        blankWindow();
        System.out.println("Please enter the id of client to show his history ..");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return new HistoryDaoImpl().getClientHistory(id);
    }

    public static boolean adminSearchClient() throws SQLException {
        blankWindow();
        System.out.println("Please enter the id of client List his info ..");
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Client client = new ClientDaoImpl().searchClient(id);
        System.out.println(
                "client_id : " + client.getClientId() +
                        "\nfirst_name : " + client.getFirstName() +
                        "\nlast_name : " + client.getLastName() +
                        "\nbalance : " + client.getBalance() +
                        "\ntitle : " + client.getTitle() + "\n");
        return true;
    }
}
