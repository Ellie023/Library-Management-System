import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Library Management System Home Screen");
            System.out.println("1: Search Book");
            System.out.println("2: Borrow Book");
            System.out.println("3: Return Book");
            System.out.println("4: User Loan Status");
            System.out.println("5: Update Member Information");
            System.out.println("6: Exit");
            System.out.print("Please select a menu: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Search Book Function");
                    break;
                case 2:
                    System.out.println("Borrow Book Function");
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.print("Enter the member's ID to borrow: ");
                    String member_name = scanner2.next();
                    System.out.print("Enter the title of the book to borrow: ");
                    int book_title = scanner2.nextInt();
                    //if(2개의 테이블에서 tutle이 없음)
                    // 대출 로직 추가 (예: Loan_management.loanBook(member_name, title);)
                    //else if(borrowing table에 있고  reservations table에 없음)
                    //예약하기
                    //else if(borrowing table에 없고 reservation table에는 있음)
                         //if(name_id 확인)
                        //일치 시 예약 로직
                        //불일치 시 대출불가

                    //else if(2개의 table 모두 있음)
                    //대출불가
                    break;
                case 3:
                    System.out.println("Return Book Function");
                    break;
                case 4:
                    System.out.println("User Loan Status");

                    break;
                case 5:
                    System.out.println("Update Member Information");
                    Scanner scanner5 = new Scanner(System.in);
                    System.out.print("Enter the ID of the member to update: ");
                    int userId5 = scanner5.nextInt();
                    System.out.print("Enter the email of the member to update: ");
                    String email = scanner5.next();
                    System.out.print("Enter the phone number of the member to update (000-1111-2222): ");
                    String phone = scanner5.next();
                    Member_management.updateMember(userId5, email, phone);
                    break;
                case 6:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid input. Please select again.");
            }
        } while (choice != 6);
        sc.close();
    }
}
