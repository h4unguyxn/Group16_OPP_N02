package com.example;

import com.example.demo.service.BookService;
import com.example.demo.service.StudentService;
import com.example.demo.test.BookServiceTest;
import com.example.demo.test.BorrowServiceTest;
import com.example.demo.test.StudentServiceTest;
import com.example.demo.service.BorrowService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        StudentService studentService = new StudentService();
        BorrowService borrowService = new BorrowService();

        while (true) {
            System.out.println("\n===== MENU CHINH =====");
            System.out.println("1. Quan ly Sach");
            System.out.println("2. Quan ly Sinh vien");
            System.out.println("3. Quan ly Phieu muon");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Chon chuc nang: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> BookServiceTest.run();
                case "2" -> StudentServiceTest.run();
                case "3" -> BorrowServiceTest.run();
                case "0" -> {
                    System.out.println("Thoat chuong trinh. Tam biet!");
                    return;
                }
                default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }
}
