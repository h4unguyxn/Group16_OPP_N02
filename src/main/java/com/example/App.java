package com.example;

import com.example.demo.service.BookService;
import com.example.demo.service.StudentService;
import com.example.demo.service.BorrowService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        StudentService studentService = new StudentService();
        BorrowService borrowService = new BorrowService();

        while (true) {
            System.out.println("\n===== MENU QUAN LY THU VIEN =====");
            System.out.println("1. Them sach");
            System.out.println("2. Xem danh sach sach");
            System.out.println("3. Cap nhat sach");

            System.out.println("4. Them sinh vien");
            System.out.println("5. Xem danh sach sinh vien");
            System.out.println("6. Cap nhat sinh vien");

            System.out.println("7. Them phieu muon");
            System.out.println("8. Xem phieu muon hom nay");
            System.out.println("9. Cap nhat phieu muon");

            System.out.println("10. Xoa sach");
            System.out.println("11. Xoa sinh vien");
            System.out.println("12. Xoa phieu muon");

            System.out.println("0. Thoat chuong trinh");
            System.out.print("Chon chuc nang: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    bookService.addBookFromKeyboard();
                    break;
                case "2":
                    bookService.displayAllBooks();
                    break;
                case "3":
                    bookService.updateBookFromKeyboard();
                    break;

                case "4":
                    studentService.addStudentFromKeyboard();
                    break;
                case "5":
                    studentService.displayAllStudents();
                    break;
                case "6":
                    studentService.updateStudentFromKeyboard();
                    break;

                case "7":
                    borrowService.addBorrowRecordFromKeyboard();
                    break;
                case "8":
                    borrowService.displayTodayBorrowRecords();
                    break;
                case "9":
                    borrowService.updateBorrowRecordFromKeyboard();
                    break;
                case "10":
                    bookService.deleteBookById();
                    break;
                case "11":
                    studentService.deleteStudentById();
                    break;
                case "12":
                    borrowService.deleteBorrowRecordById();
                    break;

                case "0":
                    System.out.println("Thoat chuong trinh. Tam biet!");
                    return;

                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }
}
