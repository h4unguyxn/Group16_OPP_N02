package com.example.demo.test;

import com.example.demo.service.BookService;

import java.util.Scanner;

public class BookServiceTest {
    public void run() {
        BookService service = new BookService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Quản lý Sách ===");
            System.out.println("1. Thêm sách mới");
            System.out.println("2. Hiển thị tất cả sách");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> service.addBookFromKeyboard();
                case 2 -> service.displayAllBooks();
                case 0 -> {
                    System.out.println("👋 Thoát chương trình!");
                    return;
                }
                default -> System.out.println("❌ Lựa chọn không hợp lệ.");
            }
        }
    }
}
