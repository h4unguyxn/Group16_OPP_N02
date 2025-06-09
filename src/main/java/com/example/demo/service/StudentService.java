package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final GenericManager<Student> manager = new GenericManager<>("students.dat");

    public void addStudentFromKeyboard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID sinh vien: ");
        String id = sc.nextLine();

        System.out.print("Nhap ten sinh vien: ");
        String name = sc.nextLine();

        System.out.print("Nhap email: ");
        String email = sc.nextLine();

        Student student = new Student(id, name, email);
        manager.add(student);
        System.out.println("Da them sinh vien: " + name);
    }

    public void displayAllStudents() {
        List<Student> students = manager.getAll();
        if (students.isEmpty()) {
            System.out.println("Khong có sinh vien nao trong danh sach.");
        } else {
            System.out.println("Danh sach sinh vien:");
            for (Student s : students) {
                System.out.printf("- %s | %s | %s\n", s.getId(), s.getName(), s.getEmail());
            }
        }
    }

    // phương thức cập nhật sinh viên từ bàn phím
    public void updateStudentFromKeyboard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID sinh vien can cap nhat: ");
        String id = sc.nextLine();

        Student existing = manager.findById(id);
        if (existing == null) {
            System.out.println("Khong tim thay sinh vien voi ID: " + id);
            return;
        }

        System.out.print("Nhap ten moi: ");
        String newName = sc.nextLine();

        System.out.print("Nhap email moi: ");
        String newEmail = sc.nextLine();

        Student updated = new Student(id, newName, newEmail);
        boolean success = manager.update(id, updated);

        if (success) {
            System.out.println("Cap nhat sinh vien thanh cong.");
        } else {
            System.out.println("Cap nhat that bai.");
        }
    }
    public void deleteStudentById() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhap ID sinh vien can xoa: ");
    String id = sc.nextLine();

    manager.delete(id);
    System.out.println("Da xoa sinh vien co ID: " + id);
    }

}
