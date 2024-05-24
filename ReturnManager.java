import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnManager {
    public static void returnBook(int user_id, String book_name) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 책 제목과 사용자 아이디로 대출 기록 조회
            String sql = "select * from books, borrowing where books.book_id=borrowing.book_id and books.title=? and borrowing.member_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book_name);
            preparedStatement.setInt(2, user_id);
            resultSet = preparedStatement.executeQuery();

            // 반납할 수 있는 책이 있음
            if (resultSet.next()) {
                int borrowingId = resultSet.getInt("borrowing_id");
                int bookId = resultSet.getInt("book_id");

                // borrowing 테이블에서 해당 대출 기록 삭제
                String sql1 = "DELETE FROM Borrowing WHERE borrowing_id = ?";
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setInt(1, borrowingId);
                preparedStatement.executeUpdate();
                System.out.println("'" + book_name + "'" + " 책이 반납되었습니다.");
            } else {
                // 회원 이름 가져오기
                String query6 = "SELECT name FROM Members WHERE member_id = ?";
                preparedStatement = connection.prepareStatement(query6);
                preparedStatement.setInt(1, user_id);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    System.out.println(name + " 회원님이 대출한 책이 아닙니다.");
                } else {
                    System.out.println("회원 정보를 찾을 수 없습니다.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
