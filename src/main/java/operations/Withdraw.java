package operations;

import ConnectionJDBC.SingleToneConnection;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;
import model.Client;

import java.sql.Connection;
import java.sql.SQLException;

public class Withdraw implements Operations {

    private static SingleToneConnection singleToneConnection = null;

    public static boolean removeFromBalance(int id, double amount) throws SQLException {
        ClientDaoImpl clientDao = new ClientDaoImpl();
        HistoryDaoImpl historyDao = new HistoryDaoImpl();
        singleToneConnection = SingleToneConnection.getInstance();
        if(amount <= 0){
            System.out.println("invalid input .. please entre positive amount greater than 1");
            return false;
        }
        if(clientDao.searchClient(id).getBalance() < amount){
            System.out.println("no enough money in your account .. please try again with smaller amount");
            return false;
        }
        try(Connection conn = singleToneConnection.establishConnection()) {
            conn.setAutoCommit(false);
            boolean removeSuccessfully = clientDao.updateClientBalance(conn, id, -amount);
            boolean withdrawHistory = historyDao.addToHistory(conn, id, "Withdraw " + amount + "$");
            if(!removeSuccessfully || !withdrawHistory){
                conn.rollback();
            } else {
                conn.commit();
            }
            return true;
        }
    }
}
