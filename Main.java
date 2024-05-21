import java.sql.Connection;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        do{
            System.out.println("도서관리 시스템 홈 화면");
            System.out.println("1 : 도서 검색");
            System.out.println("2: 대출하기");
            System.out.println("3 : 반납하기");
            System.out.println("4: 사용자별 대출 현황");
            System.out.println("5: 회원 정보 수정");
            System.out.println("6: 종료");
            System.out.println("메뉴룰 선택하세요: ");
            choice=sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("도서 검색 기능");
                    // 도서 검색 로직 추가
                    break;
                case 2:
                    System.out.println("대출하기 기능");
                    // 대출하기 로직 추가
                    break;
                case 3:
                    System.out.println("반납하기 기능");
                    // 반납하기 로직 추가
                    break;
                case 4:
                    System.out.println("사용자별 대출 현황");
                    // 사용자별 대출 현황 로직 추가
                    break;
                case 5:
                    System.out.println("회원 정보 수정");

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("수정할 회원의 id 입력: ");
                    int user_id = scanner.nextInt();
                    System.out.println("수정할 회원의 이메일 입력: ");
                    String email = scanner.next();
                    System.out.println("수정할 회원의 전화번호 입력(000-1111-2222): ");
                    String phone = scanner.next();
                    Member_management.updateMember(user_id,email,phone);
                    break;
                case 6:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }while(choice!=6);
        sc.close();

    }


}