package application.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatasourceConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/todoapp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";
    private static Connection connection;
    private static Logger logger = Logger.getGlobal();

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            logger.info("exception accured");
        }
        return connection;
    }
}
