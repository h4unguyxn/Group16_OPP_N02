package com.example.demo.test;

import com.example.demo.service.BorrowService;

import java.util.Scanner;

public class BorrowServiceTest {
    public void run() {
        BorrowService service = new BorrowService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Quản lý Mượn Sách ===");
            System.out.println("1. Thêm phiếu mượn");
            System.out.println("2. Xem danh sách mượn sách hôm nay");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> service.addBorrowRecordFromKeyboard();
                case 2 -> service.displayTodayBorrowRecords();
                case 0 -> { return; }
                default -> System.out.println("❌ Lựa chọn không hợp lệ.");
            }
        }
    }
}
