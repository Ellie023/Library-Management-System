import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String server = "jdbc:mysql://localhost:3306/library"; // 서버 주소
    private static final String user_name = "root"; // 접속자 id
    private static final String password = "1020"; // 접속자 pw

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 로드
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL 드라이버 로드 실패", e);
        }
        return DriverManager.getConnection(server, user_name, password);
    }
}


