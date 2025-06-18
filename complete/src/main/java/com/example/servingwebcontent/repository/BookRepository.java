package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Book;

public class BookRepository extends GenericManager<Book> {
    public BookRepository() {
        super("books.dat");
    }
}
