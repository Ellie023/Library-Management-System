import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookGenre {
    public static void numberOfBooksByGenre(String genre) {
 PreparedStatement pstm = null;
        ResultSet resultSet = null;
        Connection connection = DatabaseConnection.getConnection();
        try {

            // 쿼리문
            String sql = "SELECT genre, count(genre) AS total_number FROM books GROUP BY genre having genre=?";

            pstm = connection.prepareStatement(sql);
            pstm.setString(1, genre);

            resultSet = pstm.executeQuery();

            if (resultSet.next()) {
                String bookGenre = resultSet.getString("genre");
                int totalNumber = resultSet.getInt("total_number");

                System.out.println(bookGenre + " 종류의 책 보유 권수 : " + totalNumber + "권");

            } else {
                System.out.println("존재하지 않는 종류입니다.");
            }

            resultSet.close();
            pstm.close();
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}