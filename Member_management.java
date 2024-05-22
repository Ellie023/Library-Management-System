import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Member_management {
    public static void updateMember(int user_id, String email, String phone) {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "UPDATE members SET email=?, phone_number=? where member_id=?";
        PreparedStatement pstm = null;
        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, phone);
            pstm.setInt(3,user_id);
            int count = pstm.executeUpdate();
            if (count >0) {
                System.out.println("이메일과 전화번호가 변경되었습니다.");

            } else {
                System.out.println("회원 정보 수정에 실패했습니다.");
            }

            System.out.println("회원정보 수정 서비스를 종료합니다.");
            System.out.println("------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
