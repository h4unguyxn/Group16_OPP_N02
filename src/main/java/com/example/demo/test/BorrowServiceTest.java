package com.example.demo.test;

import com.example.demo.service.BorrowService;

import java.util.Scanner;

public class BorrowServiceTest {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        BorrowService borrowService = new BorrowService();

        while (true) {
            System.out.println("\n----- MENU QUAN LY PHIEU MUON -----");
            System.out.println("1. Them phieu muon");
            System.out.println("2. Xem phieu muon hom nay");
            System.out.println("3. Cap nhat phieu muon");
            System.out.println("4. Xoa phieu muon");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon chuc nang: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> borrowService.addBorrowRecordFromKeyboard();
                case "2" -> borrowService.displayTodayBorrowRecords();
                case "3" -> borrowService.updateBorrowRecordFromKeyboard();
                case "4" -> borrowService.deleteBorrowRecordById();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }
}