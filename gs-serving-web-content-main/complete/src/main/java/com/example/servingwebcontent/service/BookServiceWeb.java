package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceWeb {

    private final BookRepository repository;

    public BookServiceWeb() {
        this.repository = new BookRepository();
    }

    public void addBook(Book book) {
        try {
            repository.add(book);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void updateBook(String id, Book updatedBook) {
        try {
            repository.update(id, updatedBook);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void deleteBookById(String id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<Book> getAllBooks() {
        try {
            return repository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // Trả về danh sách rỗng nếu lỗi
        } finally {
        }
    }

    public Book findBookById(String id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Trả về null nếu lỗi
        } finally {
        }
    }
}
