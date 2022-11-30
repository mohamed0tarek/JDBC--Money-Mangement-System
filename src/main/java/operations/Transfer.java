package operations;

import ConnectionJDBC.SingleToneConnection;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transfer implements Operations {

    private static SingleToneConnection singleToneConnection = null;

    public static boolean executeTransfer(int idFrom, int idTo, double amount) throws SQLException {
        HistoryDaoImpl history = new HistoryDaoImpl();
        ClientDaoImpl clientDao = new ClientDaoImpl();
        Client client = clientDao.searchClient(idFrom);
        if (client.getBalance() < amount) {
            System.err.println("your balance has no enough money to transfer !!");
            return false;
        }
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection()) {
            conn.setAutoCommit(false);
            boolean TransferFrom = clientDao.updateClientBalance(conn, idFrom, -amount);
            boolean TransferTo = clientDao.updateClientBalance(conn, idTo, amount);
            boolean historyFrom = history.addToHistory(conn, idFrom,"Transfer "+ amount + "$ to "+idTo);
            boolean historyTo = history.addToHistory(conn, idTo, "Receive "+ amount + "$ from "+idFrom);
            System.out.println("check this " + historyFrom + " " + historyTo + " " + TransferFrom + " " + TransferTo);
            if (!TransferFrom || !TransferTo || !historyFrom || !historyTo) {
                System.out.println("rollback");
                conn.rollback();
            } else {
                conn.commit();
            }
            return true;
        }
    }

}
