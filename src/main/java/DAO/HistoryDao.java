package DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface HistoryDao {
    public boolean addToHistory(Connection conn, int ct_id, String operation) throws SQLException;
    public boolean getAllHistory() throws SQLException;
    public boolean getClientHistory(int ct_id) throws SQLException;
}
