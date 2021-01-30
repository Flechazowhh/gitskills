import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/pokergame";
    private static final String NAME = "root";
    private static final String PASSWORD = "lcf010721";
    //创建好 REL jdbc：mysql:
    public static Connection set_comment() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        return connection;
    }
}
