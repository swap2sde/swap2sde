package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Wrapper;

public class OJDBCConnecter {

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE"; // Replace with your DB URL
    String username = "SYSTEM";
    String password = "root";

    Class.forName("oracle.jdbc.driver.OracleDriver");
    return DriverManager.getConnection(jdbcURL, username, password);

    }
}
