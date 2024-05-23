import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReturnManager {
    public static void returnBook(int user_id, String book_name) {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        if (connection != null) {
            try {
                // 대출 table에서 일치하는 사용자 아이디 & 책 제목 있는지 확인
                String sql = "DELETE borrowing FROM borrowing " +
                        "JOIN books ON books.book_id = borrowing.book_id " +
                        "WHERE books.title = ? AND borrowing.member_id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,book_name);
                preparedStatement.setInt(2,user_id);
                int rowsAffected = preparedStatement.executeUpdate();

                // 반납할 수 있는 책이 있음
                if (rowsAffected > 0) {
                    System.out.println("'" + book_name + "'" + " 책이 반납되었습니다.");
                // 반납할 수 있는 책이 없음
                } else {
                    System.out.println("반납할 수 있는 책이 없습니다.");
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
