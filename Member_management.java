import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Member_management {
    public static void updateMember(int user_id, String email, String phone) {
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();
        }
        catch (
                SQLException e) {
                    System.err.println("연결 오류" + e.getMessage());
                e.printStackTrace();
        }


        String sql = "UPDATE members SET email=?, phone_number=? where member_id=?";
        PreparedStatement pstm = null;
        try {
            pstm = con.prepareStatement(sql);
            pstm.setString(1, email);
            pstm.setString(2, phone);
            pstm.setInt(3,user_id);
            int count = pstm.executeUpdate();
            if (count >0) {
                System.out.println("수정 성공");
            } else {
                System.out.println("수정 실패");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //5. DB종료
            try {
                pstm.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
