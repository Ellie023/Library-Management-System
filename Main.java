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
