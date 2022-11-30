package ConnectionJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SingleToneConnection {
    private String url = "jdbc:mysql://localhost:3306/atm";
    private String userName = "mohamed";
    private String password = "mohamed123";

    private SingleToneConnection() {
    }

    private static SingleToneConnection connection = null;

    public static SingleToneConnection getInstance() {
        if (connection == null)
            connection = new SingleToneConnection();
        return connection;
    }

    public Connection establishConnection() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
