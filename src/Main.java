import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("도서 관리 시스템");
            System.out.println("1: [종류별 책 보유 현황]");
            System.out.println("2: [대출하기]");
            System.out.println("3: [반납하기]");
            System.out.println("4: [사용자별 대출 현황]");
            System.out.println("5: [회원정보 수정]");
            System.out.println("6: [종료]");
            System.out.print("원하시는 기능의 번호를 입력해주세요: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    System.out.println("---------------------------[종류별 책 보유 현황]------------------------------");
                    Scanner scanner1 = new Scanner(System.in);
                    String genre;
                    System.out.println("보유 현황을 알고 싶은 책의 종류를 입력해주세요. ");
                    genre = scanner1.nextLine();
                    BookGenre.numberOfBooksByGenre(genre);
                    System.out.println("---------------------------------------------------------------------------");
                    break;

                case 2:
                    System.out.println("Borrow Book Function");
                    Scanner scanner2 = new Scanner(System.in);
                    int user_id2;
                    String book_title2;
                    while (true) {
                        System.out.print("Enter the member's ID to borrow: ");
                        user_id2 = scanner2.nextInt();
                        scanner2.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id2)) {
                            break;
                        }
                        else {
                            System.out.println("Invalid the member's ID. Please try again.");
                        }
                    }
                    System.out.print("Enter the title of the book to borrow: ");
                    while   (true) {
                        System.out.print("Enter the title of the book to borrow: ");
                        book_title2 = scanner2.next();
                        scanner2.nextLine();
                        if (InputValidator.is_vaild_Booktitle(book_title2)) {
                            break;
                        }
                        else {
                            System.out.println("Invalid the title of the book. Please try again.");
                        }
                    }
                    //borrowBook(user_id2,book_title2)
                    break;
                case 3:
                    System.out.println("Return Book Function");
                    Scanner scanner3 = new Scanner(System.in);
                    int user_id3;
                    String book_title3;
                    while (true) {
                        System.out.print("Enter the member's ID to return: ");
                        user_id3 = scanner3.nextInt();
                        scanner3.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id3)) {
                            break;
                        }
                        else {
                            System.out.println("Invalid the member's ID. Please try again.");
                        }
                    }
                    while (true) {
                        System.out.print("Enter the title of the book to return: ");
                        book_title3 = scanner3.next();
                        scanner3.nextLine();
                        if (InputValidator.is_vaild_Booktitle(book_title3)) {
                            break;
                        }
                        else {
                            System.out.println("Invalid the title of the book. Please try again.");
                        }
                    }
                    //ReturnManager.returnBook(user_id3,book_title3)
                    break;
                case 4:
                    System.out.println("User Loan Status");

                    break;
                case 5:
                    System.out.println("Update Member Information");
                    Scanner scanner5 = new Scanner(System.in);
                    int user_id5;
                    String email,phone;
                    while (true) {
                        System.out.print("Enter the ID of the member to update: ");
                        user_id5 = scanner5.nextInt();
                        scanner5.nextLine();
                        if (InputValidator.is_Valid_Memberid(user_id5)) {
                            break;
                        }
                        else {
                            System.out.println("Invalid the member's ID. Please try again.");
                        }
                    }
                        System.out.print("Enter the email of the member to update: ");
                        email = scanner5.next();
                        System.out.print("Enter the phone number of the member to update (000-1111-2222): ");
                        phone = scanner5.next();
                        Member_management.updateMember(user_id5, email, phone);
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
