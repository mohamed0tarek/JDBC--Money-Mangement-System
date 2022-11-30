package DAO;

import model.Client;

import java.sql.Connection;
import java.sql.SQLException;

public interface ClientDao {
    public boolean addClient(Client ct) throws SQLException;

    public boolean deleteClient(int id) throws SQLException;

    public Client searchClient(int id) throws SQLException;

    public boolean updateClientBalance(Connection conn, int id, double amount) throws SQLException;

    public boolean getAllClients() throws SQLException;
}
