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
        repository.add(book);
    }

    public void updateBook(String id, Book updatedBook) {
        repository.update(id, updatedBook);
    }

    public void deleteBookById(String id) {
        repository.delete(id);
    }

    public List<Book> getAllBooks() {
        return repository.getAll();
    }

    public Book findBookById(String id) {
        return repository.findById(id);
    }
}
