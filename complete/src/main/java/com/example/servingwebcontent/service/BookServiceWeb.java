package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceWeb {

    private final BookRepository repository;

    public BookServiceWeb(BookRepository repository) {
        this.repository = repository;
    }

    public void addBook(Book book) {
        repository.save(book);
    }

    public void updateBook(String id, Book updatedBook) {
        if (repository.existsById(id)) {
            repository.save(updatedBook);
        }
    }

    public void deleteBookById(String id) {
        repository.deleteById(id);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public Book findBookById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Book> filterByGenre(String genre) {
        return repository.findByGenreContainingIgnoreCase(genre);
    }

    public List<Book> filterByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author);
    }
}
