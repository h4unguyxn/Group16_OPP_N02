package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.BorrowRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BorrowService {
    private final GenericManager<BorrowRecord> manager = new GenericManager<>("borrow_records.dat");

    public void addBorrowRecordFromKeyboard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID phieu muon: ");
        String id = sc.nextLine();

        System.out.print("Nhap ID sinh vien: ");
        String studentId = sc.nextLine();

        System.out.print("Nhap ID sach: ");
        String bookId = sc.nextLine();

        LocalDate today = LocalDate.now();
        BorrowRecord record = new BorrowRecord(id, studentId, bookId, today);
        manager.add(record);
        System.out.println("Da them phieu muon: " + id);
    }

    public void displayTodayBorrowRecords() {
        List<BorrowRecord> records = manager.getAll();
        LocalDate today = LocalDate.now();

        System.out.println("Danh sach muon sach hom nay:");
        records.stream()
                .filter(r -> r.getBorrowDate().equals(today))
                .forEach(r -> System.out.printf("- %s | SV: %s | Sach: %s | Ngay: %s\n",
                        r.getId(), r.getStudentId(), r.getBookId(), r.getBorrowDate()));
    }

    //  thức cập nhật thông tin bản ghi mượn
    public void updateBorrowRecordFromKeyboard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID phieu muon can cap nhat: ");
        String id = sc.nextLine();

        BorrowRecord existing = manager.findById(id);
        if (existing == null) {
            System.out.println("Khong tim thay phieu muon voi ID: " + id);
            return;
        }

        System.out.print("Nhap ID sach moi: ");
        String newBookId = sc.nextLine();

        System.out.print("Nhap ngay muon moi (yyyy-mm-dd): ");
        String dateInput = sc.nextLine();
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Ngay khong hop le. Dinh dang dung la yyyy-mm-dd.");
            return;
        }

        // giữ nguyên studentId
        BorrowRecord updated = new BorrowRecord(id, existing.getStudentId(), newBookId, newDate);
        boolean success = manager.update(id, updated);

        if (success) {
            System.out.println("Cap nhat phieu muon thanh cong.");
        } else {
            System.out.println("Cap nhat that bai.");
        }
    }
    public void deleteBorrowRecordById() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhap ID phieu muon can xoa: ");
    String id = sc.nextLine();

    manager.delete(id);
    System.out.println("Da xoa phieu muon co ID: " + id);
    }

}
