import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InputValidator {

    public static boolean is_Valid_Memberid(int member_id){

        String checkSQL="SELECT member_id from Members where member_id=?";
        try (Connection con =DatabaseConnection.getConnection();
             PreparedStatement pstmt =con.prepareStatement(checkSQL)){
            pstmt.setInt(1, member_id);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); //

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean is_vaild_Bookid(int book_id){

        String checkSQL="SELECT book_id from Books where book_id=?";
        try(Connection con =DatabaseConnection.getConnection();
        PreparedStatement pstmt =con.prepareStatement(checkSQL)){
            pstmt.setInt(1,book_id);
            ResultSet rs =pstmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean is_vaild_Booktitle(String title){
        String checkSQL="SELECT title from Books where title=?";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkSQL)){
            pstmt.setString(1,title);
            ResultSet rs=pstmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean is_vaild_name(String name){
        String checkSQL="select name from Members where name=?";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkSQL)){
            pstmt.setString(1,name);
            ResultSet rs=pstmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static boolean is_vaild_bookgenre(String genre){
        String checkSQL="select genre from Books where genre=?";
        try(Connection con=DatabaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(checkSQL)){
            pstmt.setString(1,genre);
            ResultSet rs=pstmt.executeQuery();
            return rs.next();
        }
        catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
