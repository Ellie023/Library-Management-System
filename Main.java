import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("------------------------------------------");
            System.out.println("Library Management System");
            System.out.println("1: 종류별 책 보유 현황 보기");
            System.out.println("2: 책 대출");
            System.out.println("3: 책 반납");
            System.out.println("4: 사용자별 대출 현황");
            System.out.println("5: 회원 정보 수정");
            System.out.println("6: 종료");
            System.out.print("메뉴를 선택하세요: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("[[종류별 책 보유 현황 보기]]");
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.print("책 종류를 입력하세요: ");
                    String genre =scanner1.next();
                    BookGenre.numberOfBooksByGenre(genre);
                    break;
                case 2:
                    System.out.println("[[책 대출하기]]");
                    Scanner scanner2 = new Scanner(System.in);
                    int user_id2;
                    String book_title2;
                    while (true) {
                        System.out.print("회원의 ID를 입력하세요: ");
                        user_id2 = scanner2.nextInt();
                        scanner2.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id2)) {
                            break;
                        }
                        else {
                            System.out.println("존재하지 않는 회원입니다. 회원의 ID를 다시 입력하세요.");
                        }
                    }
                    while   (true) {
                        System.out.print("대출할 책의 제목을 입력하세요: ");
                        book_title2 = scanner2.nextLine();
                        System.out.println(book_title2);
                        if (InputValidator.is_vaild_Booktitle(book_title2)) {
                            break;
                        }
                        else {
                            System.out.println("책이 존재하지 않습니다. 책의 제목을 다시 입력하세요. ");
                        }
                    }
                    BorrowManager.borrowBook(user_id2,book_title2);
                    break;
                case 3:
                    System.out.println("[[책 반납하기]]");
                    Scanner scanner3 = new Scanner(System.in);
                    int user_id3;
                    String book_title3;
                    while (true) {
                        System.out.print("반납을 하는 회원의 ID를 입력하세요: ");
                        user_id3 = scanner3.nextInt();
                        scanner3.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id3)) {
                            break;
                        }
                        else {
                            System.out.println("존재하지 않는 회원입니다. 회원의 ID를 다시 입력하세요.");
                        }
                    }
                    while (true) {
                        System.out.print("반납 할 책의 제목을 입력하세요: ");
                        book_title3 = scanner3.nextLine();
                        scanner3.nextLine();
                        if (InputValidator.is_vaild_Booktitle(book_title3)) {
                            break;
                        }
                        else {
                            System.out.println("책이 존재하지 않습니다. 책의 제목을 다시 입력하세요.");
                        }
                    }
                    ReturnManager.returnBook(user_id3,book_title3);
                    break;
                case 4:
                    System.out.println("[[사용자별 대출 현황]]");
                    Scanner scanner4 = new Scanner(System.in);
                    int user_id4;
                    while (true) {
                        System.out.print("회원의 ID를 입력하세요: ");
                        user_id4=scanner4.nextInt();
                        scanner4.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id4)) {
                            break;
                        }
                        else {
                            System.out.println("존재하지 않는 회원의 ID입니다. 다시 입력하세요.");
                        }
                    }
                    select1.printBorrowedBooks(user_id4);

                    break;
                case 5:
                    System.out.println("[[회원 정보 수정하기]]");
                    Scanner scanner5 = new Scanner(System.in);
                    int user_id5;
                    String email,phone;
                    while (true) {
                        System.out.print("수정 할 회원의 ID를 입력하세요: ");
                        user_id5 = scanner5.nextInt();
                        scanner5.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id5)) {
                            break;
                        }
                        else {
                            System.out.println("존재하지 않는 회원의 ID입니다. 다시 입력하세요.");
                        }
                    }
                        System.out.print("새로운 이메일 주소를 입력하세요: ");
                        email = scanner5.next();
                        System.out.print("새로운 전화번호를 입력하세요(000-1111-2222): ");
                        phone = scanner5.next();
                        Member_management.updateMember(user_id5, email, phone);
                        break;
                case 6:
                    System.out.println("안녕히가십시오.");
                    break;
                default:
                    System.out.println("존재하지 않는 메뉴입니다. 다시 선택하세요.");
            }
            System.out.println("------------------------------------------");
        } while (choice != 6);
        sc.close();
    }
}
