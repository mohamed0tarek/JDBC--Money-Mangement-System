package DAO;

import ConnectionJDBC.SingleToneConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDaoImpl implements HistoryDao {

    SingleToneConnection singleToneConnection = null;

    @Override
    public boolean addToHistory(Connection conn, int ct_id, String operation) throws SQLException {
        String sql = "insert into history(ct_id,operation) values(?,?)";
        try(PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, ct_id);
            ps.setString(2, operation);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean getAllHistory() throws SQLException {
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection();
             PreparedStatement ps = conn.prepareStatement("select * from history order by history_id desc");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("ct_id") + "    " +
                        rs.getString("operation") + "    " +
                        rs.getTimestamp("date"));
            }
            return true;
        }
    }

    @Override
    public boolean getClientHistory(int ct_id) throws SQLException {
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection();
             PreparedStatement ps = conn.prepareStatement("select * from history where ct_id =" + ct_id +
                     " order by history_id desc");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("ct_id") + "  " +
                        rs.getString("operation") + "  " +
                        rs.getTime("date"));
            }
            return true;
        }
    }
}
