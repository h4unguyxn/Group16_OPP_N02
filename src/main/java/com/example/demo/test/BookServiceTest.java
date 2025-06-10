package com.example.demo.test;

import com.example.demo.service.BookService;

import java.util.Scanner;

public class BookServiceTest {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();

        while (true) {
            System.out.println("\n----- MENU QUAN LY SACH -----");
            System.out.println("1. Them sach");
            System.out.println("2. Xem danh sach sach");
            System.out.println("3. Cap nhat sach");
            System.out.println("4. Xoa sach");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon chuc nang: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> bookService.addBookFromKeyboard();
                case "2" -> bookService.displayAllBooks();
                case "3" -> bookService.updateBookFromKeyboard();
                case "4" -> bookService.deleteBookById();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }
}
