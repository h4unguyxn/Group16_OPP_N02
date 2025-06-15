package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Book;
import com.example.servingwebcontent.service.BookServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookServiceWeb bookService;

    @Autowired
    public BookController(BookServiceWeb bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Book book = bookService.findBookById(id);
        if (book == null) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        bookService.updateBook(book.getId(), book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        bookService.deleteBookById(id);
        return "redirect:/books";
    }

    // Lọc sách (tùy chọn mở rộng)
    @GetMapping("/filter")
    public String filterBooks(@RequestParam(required = false) String genre,
                               @RequestParam(required = false) String author,
                               Model model) {
        List<Book> books = bookService.getAllBooks();
        if (genre != null && !genre.isEmpty()) {
            books = books.stream().filter(b -> b.getGenre().equalsIgnoreCase(genre)).toList();
        }
        if (author != null && !author.isEmpty()) {
            books = books.stream().filter(b -> b.getAuthor().equalsIgnoreCase(author)).toList();
        }
        model.addAttribute("books", books);
        return "books/list";
    }
}
