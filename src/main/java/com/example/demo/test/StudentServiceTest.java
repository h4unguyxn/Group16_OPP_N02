package com.example.demo.test;

import com.example.demo.service.StudentService;

import java.util.Scanner;

public class StudentServiceTest {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();

        while (true) {
            System.out.println("\n----- MENU QUAN LY SINH VIEN -----");
            System.out.println("1. Them sinh vien");
            System.out.println("2. Xem danh sach sinh vien");
            System.out.println("3. Cap nhat sinh vien");
            System.out.println("4. Xoa sinh vien");
            System.out.println("0. Quay lai menu chinh");
            System.out.print("Chon chuc nang: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> studentService.addStudentFromKeyboard();
                case "2" -> studentService.displayAllStudents();
                case "3" -> studentService.updateStudentFromKeyboard();
                case "4" -> studentService.deleteStudentById();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Lua chon khong hop le. Vui long thu lai.");
            }
        }
    }
}
