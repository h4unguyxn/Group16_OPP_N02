package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByGenreContainingIgnoreCase(String genre);
    List<Book> findByAuthorContainingIgnoreCase(String author);
}
