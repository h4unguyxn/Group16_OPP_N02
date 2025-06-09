package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.Book;

import java.util.List;
import java.util.Scanner;

public class BookService {
    private final GenericManager<Book> manager = new GenericManager<>("books.dat");

    public void addBookFromKeyboard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID sach: ");
        String id = sc.nextLine();

        System.out.print("Nhap tieu de: ");
        String title = sc.nextLine();

        System.out.print("Nhap tac gia: ");
        String author = sc.nextLine();

        System.out.print("Nhap the loai: ");
        String genre = sc.nextLine();

        Book book = new Book(id, title, author, genre);
        manager.add(book);
        System.out.println("Da them sach: " + title);
    }

    public void displayAllBooks() {
        List<Book> books = manager.getAll();
        if (books.isEmpty()) {
            System.out.println("Khong co sach nao trong danh sach.");
        } else {
            System.out.println("Danh sach sach:");
            for (Book b : books) {
                System.out.printf("- %s | %s | %s | %s%n", b.getId(), b.getTitle(), b.getAuthor(), b.getGenre());
            }
        }
    }

    // phương thức cập nhật thông tin sách từ bàn phím
    public void updateBookFromKeyboard() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap ID sach can cap nhat: ");
        String id = sc.nextLine();

        Book existing = manager.findById(id);
        if (existing == null) {
            System.out.println("Khong tim thay sach voi ID: " + id);
            return;
        }

        System.out.print("Nhap tieu de moi: ");
        String newTitle = sc.nextLine();

        System.out.print("Nhap tac gia moi: ");
        String newAuthor = sc.nextLine();

        System.out.print("Nhap the loai moi: ");
        String newGenre = sc.nextLine();

        Book updated = new Book(id, newTitle, newAuthor, newGenre);
        boolean success = manager.update(id, updated);
        if (success) {
            System.out.println("Đa cap nhat sach thanh cong.");
        } else {
            System.out.println("Cap nhat that bai.");
        }
    }
    public void deleteBookById() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhap ID sach can xoa: ");
    String id = sc.nextLine();

    manager.delete(id);
    System.out.println("Da xoa sach co ID: " + id);
    }

}
