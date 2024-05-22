import java.sql.*;
import java.util.Scanner;


//사용자가 자신의 ID를 입력하면, 자신의 도서 대출현황을 알 수 있는 menu
public class select1 {
    public static void printBorrowedBooks(int ID) {
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();
        }
        catch (
                SQLException e) {
            System.err.println("There was a connection error." + e.getMessage());
            e.printStackTrace();
        }
        PreparedStatement selectBooksStmt = null;
        ResultSet booksResultSet = null;
        try {
            // SQL QUERY - createSchema.sql에서 만든 MemberBorrowedBooks라는 VIEW를 활용.
            // 해당 view의 member_id가 사용자의 입력값(ID)와 일치하는 튜플만을 SELECT
            String selectBooksSQL = "SELECT * FROM MemberBorrowedBooks WHERE member_id = ? ";
            selectBooksStmt = con.prepareStatement(selectBooksSQL); //PreparedStatement의 selectBooksStmt 객체를 생성.
            selectBooksStmt.setInt(1, ID); //setString 메서드를 통해 사용자의 input으로 받은 ID 값이 SQL 쿼리의 ? 자리에 바인딩
            booksResultSet = selectBooksStmt.executeQuery(); //selectBooksSQL을 실행하고 그 결과를 가져와서 ResultSet 객체인 booksResultSet에 저장

            if (!booksResultSet.next()) {
                //bookResultSet의 값을 읽기. 값이 없다면 => "대출한 책이 없습니다"라고 출력
                System.out.println(ID + "님은 대출한 책이 없습니다.");
            } else {
                //값이 있다면 대출한 책 목록 출력.
                String memberName = booksResultSet.getString("member_name");
                String memberID = booksResultSet.getString("member_ID");
                System.out.println("-----------[ " + memberName + "(" + memberID + ")" + "님이 대출한 책 목록 ]-----------");
                System.out.println("------------------- 책 제목 | 저자 ----------------------");
                do {
                    String bookTitle = booksResultSet.getString("book_title");
                    String bookAuthor = booksResultSet.getString("book_author");
                    System.out.printf("%-23s|   %-10s",bookTitle,bookAuthor);
                    System.out.println();
                } while (booksResultSet.next());
                System.out.println("-------------------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                //null이 아닌 경우에만 close() 메서드를 호출하여 객체를 닫음- close()의 NullPointerException 방지.
                if (booksResultSet != null) booksResultSet.close();
                if (selectBooksStmt != null) selectBooksStmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 연결을 종료하는 메소드
    public static void closeConnection(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}