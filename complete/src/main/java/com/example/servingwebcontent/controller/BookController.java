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
        try {
            List<Book> books = bookService.getAllBooks();
            model.addAttribute("books", books);
            return "books/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            model.addAttribute("book", new Book());
            return "books/add";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        try {
            bookService.addBook(book);
            return "redirect:/books";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            Book book = bookService.findBookById(id);
            if (book == null) {
                return "redirect:/books";
            }
            model.addAttribute("book", book);
            return "books/edit";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @PostMapping("/edit")
    public String updateBook(@ModelAttribute Book book) {
        try {
            bookService.updateBook(book.getId(), book);
            return "redirect:/books";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id) {
        try {
            bookService.deleteBookById(id);
            return "redirect:/books";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/filter")
    public String filterBooks(@RequestParam(required = false) String genre,
                              @RequestParam(required = false) String author,
                              Model model) {
        try {
            List<Book> books = bookService.getAllBooks();

            if (genre != null && !genre.isBlank()) {
                books = books.stream()
                        .filter(b -> b.getGenre() != null && b.getGenre().equalsIgnoreCase(genre))
                        .toList();
            }

            if (author != null && !author.isBlank()) {
                books = books.stream()
                        .filter(b -> b.getAuthor() != null && b.getAuthor().equalsIgnoreCase(author))
                        .toList();
            }

            model.addAttribute("books", books);
            return "books/list";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
