package operations;

import ConnectionJDBC.SingleToneConnection;
import DAO.ClientDaoImpl;
import DAO.HistoryDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Deposite implements Operations {

    private static SingleToneConnection singleToneConnection = null;

    public static boolean addToBalance(int id, double amount) throws SQLException {
        if(amount <=0.9 ){
            System.out.println("invalid input .. please entre positive amount greater than 1");
            return false;
        }
        ClientDaoImpl clientDao = new ClientDaoImpl();
        HistoryDaoImpl historyDao = new HistoryDaoImpl();
        singleToneConnection = SingleToneConnection.getInstance();
        try(Connection conn = singleToneConnection.establishConnection()){
            conn.setAutoCommit(false);
            boolean addSuccessfully = clientDao.updateClientBalance(conn, id, amount);
            boolean depositeHistory = historyDao.addToHistory(conn, id, "Deposite " + amount + "$ ");
            if(!addSuccessfully || !depositeHistory){
                conn.rollback();
            } else {
                conn.commit();
            }
            return true;
        }
    }
}
