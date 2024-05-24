import javax.print.DocFlavor;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class
BorrowManager {

    // 날짜 계산해주는 함수 (+1일, +14일 등)
    public static String addDays(String stringDate, int daysToAdd) {
        LocalDate date = LocalDate.parse(stringDate);

        LocalDate newDate = date.plusDays(daysToAdd);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return newDate.format(formatter);
    }

    public static void borrowBook(int user_id, String book_name) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String date = ""; // 책 반납 날짜 저장 변수
        String b_id = ""; // 책 아이디 저장 변수


        if (connection != null) {
            try {
                boolean borrow = false;// 대중가능 =false, 대출중 =true
                boolean reservation = false; //예약가능 = false 예약중 =true
                // 대출중인 책인지 확인
                String query =
                        "SELECT * FROM borrowing, books WHERE books.book_id = borrowing.book_id AND title = ?";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, book_name);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    borrow = true;//대출중
                }
                //예약 중인 책인지 확인
                String query2 =
                        "SELECT * FROM reservations, books WHERE books.book_id = reservations.book_id AND title = ?";
                preparedStatement = connection.prepareStatement(query2);
                preparedStatement.setString(1, book_name);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    reservation = true;//예약중
                }

                //대출가능,예약도 없음
                if (!borrow && !reservation) {

                    System.out.println("대출할 수 있는 책입니다.");

                    // 책 제목으로 책 아이디 가져오기
                    String query6 =
                            "SELECT book_id FROM books WHERE title = ?";
                    try {
                        preparedStatement = connection.prepareStatement(query6);
                        preparedStatement.setString(1, book_name);
                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            b_id = resultSet.getString("book_id");
                        }

                        // 오늘 날짜 구하기
                        LocalDate today = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = today.format(formatter);


                        // 대출 table에 INSERT 하여 대출하기
                        String query5 =
                                "INSERT INTO borrowing (book_id, member_id, borrow_date, return_date, borrowing_status) VALUES (?, ?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query5);
                        preparedStatement.setString(1, b_id);
                        preparedStatement.setInt(2, user_id);
                        preparedStatement.setString(3, (formattedDate));
                        preparedStatement.setString(4, (addDays(formattedDate, 14)));
                        preparedStatement.setString(5, "대출중");
                        preparedStatement.executeUpdate();
                        System.out.println("'" + book_name + "'" + " 책이 대출되었습니다.");


                        System.out.println("반납 기한은 " + Date.valueOf(addDays(formattedDate, 14)) + "까지입니다.");
                        System.out.println("대출/예약 서비스를 종료합니다.");
                        System.out.println("------------------------------------------");
                    } catch (SQLException e) {
                        e.printStackTrace();


                    }
                    return;
                }
               //대출가능 예약자 있음
                else if (!borrow && reservation) {
                    //책 예약자인지 확인하기
                    String query3 = "select * from books,reservations where books.book_id=reservations.book_id and books.title=? and reservations.member_id=?; ";
                    preparedStatement = connection.prepareStatement(query3);
                    preparedStatement.setString(1, book_name);
                    preparedStatement.setInt(2, user_id);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        // 예약했던 책인지 확인하고 DELETE
                        String sql = "DELETE reservations FROM reservations " +
                                "JOIN books ON books.book_id = reservations.book_id " +
                                "WHERE books.title = ? AND reservations.member_id = ?";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, book_name);
                        preparedStatement.setInt(2, user_id);
                       preparedStatement.executeUpdate();
                        System.out.println("예약하셨던 책 " + "'" + book_name + "'" + " 이 대출되었습니다.");
                        // 책 제목으로 책 아이디 가져오기
                        String query6 =
                                "SELECT book_id FROM books WHERE title = ?";
                            preparedStatement = connection.prepareStatement(query6);
                            resultSet = preparedStatement.executeQuery();
                            if (resultSet.next()) {
                                b_id = resultSet.getString("book_id");
                            }

                            // 오늘 날짜 구하기
                            LocalDate today = LocalDate.now();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            String formattedDate = today.format(formatter);


                            // 대출 table에 INSERT 하여 대출하기
                            String query5 =
                                    "INSERT INTO borrowing (book_id, member_id, borrow_date, return_date, borrowing_status) VALUES (?, ?, ?, ?, ?)";
                            preparedStatement = connection.prepareStatement(query5);
                            preparedStatement.setString(1, b_id);
                            preparedStatement.setInt(2, user_id);
                            preparedStatement.setString(3, (formattedDate));
                            preparedStatement.setString(4, (addDays(formattedDate, 14)));
                            preparedStatement.setString(5, "대출중");
                            preparedStatement.executeUpdate();
                            System.out.println("'" + book_name + "'" + " 책이 대출되었습니다.");
                        System.out.println("반납 기한은 " + Date.valueOf(addDays(formattedDate, 14)) + "까지입니다.");
                        System.out.println("대출/예약 서비스를 종료합니다.");
                        System.out.println("------------------------------------------");
                        return;
                    } else {
                        System.out.println("이미 예약중인 책입니다.");
                        System.out.println("대출과 예약이 모두 불가능합니다.");
                        System.out.println("대출/예약 서비스를 종료합니다.");
                        System.out.println("------------------------------------------");
                        return;
                    }

                }
                //대출불가, 예약 가능
                else if (borrow && !reservation) {
                    System.out.println("예약할 수 있는 책입니다. 예약하시겠습니까? (예 / 아니오): ");
                    String reserve = scanner.next();
                    if (reserve.equals("예")) {
                        // 예약을 위해 반납날짜와 책 아이디 가져오기
                        String query8 =
                                "SELECT borrowing.book_id, borrowing.return_date FROM borrowing, books WHERE books.book_id = borrowing.book_id AND title = ?";
                        preparedStatement = connection.prepareStatement(query8);
                        preparedStatement.setString(1, book_name);
                        resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            date = resultSet.getString("return_date");
                            b_id = resultSet.getString("book_id");
                        }


                        // 예약하기: 예약 table에 삽입
                        String query4 =
                                "INSERT INTO reservations (book_id, member_id, reservation_date, reservation_status) VALUES (?, ?, ?, ?)";
                        preparedStatement = connection.prepareStatement(query4);
                        preparedStatement.setString(1, b_id);
                        preparedStatement.setInt(2, user_id);
                        preparedStatement.setString(3, (addDays(date, 1)));
                        preparedStatement.setString(4, "예약중");
                        preparedStatement.executeUpdate();
                        LocalDate today = LocalDate.now();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = today.format(formatter);
                        System.out.println("'" + book_name + "'" + "책이 예약되었습니다.");
                        System.out.println("대출할 수 있는 날짜는 " + (addDays(date, 1)) + "부터입니다.");
                        System.out.println("대출/예약 서비스를 종료합니다.");
                        System.out.println("------------------------------------------");
                        return;
                    } else {//예약을 원하지 않음.
                        System.out.println("감사합니다.");
                        System.out.println("대출/예약 서비스를 종료합니다.");
                        System.out.println("------------------------------------------");
                        return;
                    }
                }


                // 대출불가, 예약불가
                else {
                    System.out.println("대출과 예약이 모두 불가능합니다.");
                    System.out.println("대출/예약 서비스를 종료합니다.");
                    System.out.println("------------------------------------------");
                    return;


                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}
