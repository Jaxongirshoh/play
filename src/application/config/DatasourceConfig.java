package application.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatasourceConfig {
    private static final String URL = "jdbc:postgres://localhost:5432/todolist";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException("e");
        }
        return connection;
    }
}
