package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.Book;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
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

    public void filterBooksByCategory() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhập thể loại cần lọc: ");
    String category = sc.nextLine();

    List<Book> filteredBooks = manager.getAll().stream()
            .filter(book -> book.getGenre().equalsIgnoreCase(category))
            .toList();

    displayFilteredBooks(filteredBooks);
}

    public void filterBooksByAuthor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên tác giả cần lọc: ");
        String author = sc.nextLine();

        List<Book> filteredBooks = manager.getAll().stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .toList();

        displayFilteredBooks(filteredBooks);
    }

    // Hiển thị danh sách sách đã lọc
    private void displayFilteredBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Không tìm thấy sách phù hợp.");
        } else {
            System.out.println("Danh sách sách lọc được:");
            books.forEach(book -> System.out.printf("- %s | %s | %s | %s%n", 
                    book.getId(), book.getTitle(), book.getAuthor(), book.getGenre()));
        }
    }
    public List<Book> getAllBooks() {
    return manager.getAll();
    }

    public void addBook(Book book) {
        manager.add(book);
    }

    public Book findBookById(String id) {
        return manager.findById(id);
    }

    public boolean updateBook(String id, Book updated) {
        return manager.update(id, updated);
    }

    public void deleteBookById(String id) {
    manager.delete(id);
}

}
