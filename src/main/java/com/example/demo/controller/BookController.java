package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.service.BookServiceWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceWeb bookService;

    @Autowired
    public BookController(BookServiceWeb bookService) {
        this.bookService = bookService;
    }

    // Hiển thị danh sách sách
    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list"; // Giao diện: templates/books/list.html
    }

    // Hiển thị form thêm sách mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add"; // Giao diện: templates/books/add.html
    }

    // Xử lý khi submit form thêm sách
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    // Hiển thị form chỉnh sửa sách
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "books/edit"; // Giao diện: templates/books/edit.html
    }

    // Xử lý khi submit form chỉnh sửa sách
    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book.getId(), book);
        return "redirect:/books";
    }

    // Xóa sách theo ID
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    // Lọc sách
    @GetMapping("/filter")
    public String filterBooks(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String author,
            Model model) {

        List<Book> books = bookService.getAllBooks();

        if (genre != null && !genre.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                    .toList();
        }

        if (author != null && !author.isEmpty()) {
            books = books.stream()
                    .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                    .toList();
        }

        model.addAttribute("books", books);
        return "books/list";
    }

}
