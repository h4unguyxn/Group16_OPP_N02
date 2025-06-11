package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceWeb {
    private final GenericManager<Book> manager = new GenericManager<>("books.dat");

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
