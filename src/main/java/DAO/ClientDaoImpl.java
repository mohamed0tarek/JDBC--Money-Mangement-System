package DAO;

import ConnectionJDBC.SingleToneConnection;
import model.Client;
import model.Titles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDaoImpl implements ClientDao {
    SingleToneConnection singleToneConnection = null;

    @Override
    public boolean addClient(Client ct) throws SQLException {
        if(ct.getBalance() < 0 ){
            System.out.println("invalid balance amount .. ");
            return false;
        }
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection();
             PreparedStatement ps = conn.prepareStatement("insert into client values (?,?,?,?,?,?)")) {
            ps.setInt(1, ct.getClientId());
            ps.setString(2, ct.getPassword());
            ps.setString(3, ct.getFirstName());
            ps.setString(4, ct.getLastName());
            ps.setDouble(5, ct.getBalance());
            ps.setString(6,ct.getTitle().toString());
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteClient(int id) throws SQLException {
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection();
             PreparedStatement ps = conn.prepareStatement("delete from client where client_id = ?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public Client searchClient(int id) throws SQLException {
        Client ct = new Client();
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection();
             PreparedStatement ps = conn.prepareStatement("select * from client where client_id = " + id);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ct.setClientId(rs.getInt("client_id"));
                ct.setPassword(rs.getString("password"));
                ct.setFirstName(rs.getString("first_name"));
                ct.setLastName(rs.getString("last_name"));
                ct.setBalance(rs.getDouble("balance"));
                ct.setTitle(Titles.valueOf(rs.getString("title")));
            }
        }
        return ct;
    }

    @Override
    public boolean updateClientBalance(Connection conn, int id, double amount) throws SQLException {
        String sql = """
                update client
                set balance = balance + ?
                where client_id = ?""";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public boolean getAllClients() throws SQLException {
        singleToneConnection = SingleToneConnection.getInstance();
        try (Connection conn = singleToneConnection.establishConnection();
             PreparedStatement ps = conn.prepareStatement("select * from client");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("client_id") + "  " +
                        rs.getString("first_name") + "  " +
                        rs.getString("last_name") + "  " +
                        rs.getDouble("balance") + "  " +
                        rs.getString("title"));
            }
        }
        return true;
    }
}
