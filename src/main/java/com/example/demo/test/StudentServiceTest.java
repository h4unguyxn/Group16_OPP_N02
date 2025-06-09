package com.example.demo.test;

import com.example.demo.service.StudentService;

import java.util.Scanner;

public class StudentServiceTest {
    public void run() {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu Quản lý Sinh viên ===");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("0. Quay lại");
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> service.addStudentFromKeyboard();
                case 2 -> service.displayAllStudents();
                case 0 -> { return; }
                default -> System.out.println("❌ Lựa chọn không hợp lệ.");
            }
        }
    }
}
