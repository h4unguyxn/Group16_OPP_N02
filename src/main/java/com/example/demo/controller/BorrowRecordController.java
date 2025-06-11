package com.example.demo.controller;

import com.example.demo.model.BorrowRecord;
import com.example.demo.service.BorrowRecordServiceWeb;
import com.example.demo.service.StudentServiceWeb;
import com.example.demo.service.BookServiceWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/borrowrecords")
public class BorrowRecordController {

    private final BorrowRecordServiceWeb borrowRecordService;
    private final StudentServiceWeb studentService;
    private final BookServiceWeb bookService;

    @Autowired
    public BorrowRecordController(BorrowRecordServiceWeb borrowRecordService,
                                   StudentServiceWeb studentService,
                                   BookServiceWeb bookService) {
        this.borrowRecordService = borrowRecordService;
        this.studentService = studentService;
        this.bookService = bookService;
    }

    // Hiển thị danh sách các bản ghi mượn sách
    @GetMapping
    public String listBorrowRecords(Model model) {
        model.addAttribute("borrowrecords", borrowRecordService.getAllBorrowRecords());
        return "borrowrecords/list";  // templates/borrowrecords/list.html
    }

    // Hiển thị form thêm bản ghi mượn sách mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("borrowrecord", new BorrowRecord());
        return "borrowrecords/add";  // templates/borrowrecords/add.html
    }

    // Xử lý khi submit form thêm bản ghi mượn
    @PostMapping("/add")
    public String addBorrowRecord(@ModelAttribute BorrowRecord borrowRecord, Model model) {
        boolean hasError = false;

        if (studentService.findStudentById(borrowRecord.getStudentId()) == null) {
            model.addAttribute("studentError", "ID sinh viên không tồn tại.");
            hasError = true;
        }

        if (bookService.findBookById(borrowRecord.getBookId()) == null) {
            model.addAttribute("bookError", "ID sách không tồn tại.");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("borrowrecord", borrowRecord);
            return "borrowrecords/add";
        }

        borrowRecordService.addBorrowRecord(borrowRecord);
        return "redirect:/borrowrecords";
    }

    // Hiển thị form chỉnh sửa bản ghi mượn
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        BorrowRecord record = borrowRecordService.findBorrowRecordById(id);
        if (record == null) {
            return "redirect:/borrowrecords";
        }
        model.addAttribute("borrowrecord", record);
        return "borrowrecords/edit";
    }

    // Xử lý khi submit form chỉnh sửa
    @PostMapping("/edit")
    public String updateBorrowRecord(@ModelAttribute BorrowRecord updatedRecord, Model model) {
        boolean hasError = false;

        if (studentService.findStudentById(updatedRecord.getStudentId()) == null) {
            model.addAttribute("studentError", "ID sinh viên không tồn tại.");
            hasError = true;
        }

        if (bookService.findBookById(updatedRecord.getBookId()) == null) {
            model.addAttribute("bookError", "ID sách không tồn tại.");
            hasError = true;
        }

        if (hasError) {
            model.addAttribute("borrowrecord", updatedRecord);
            return "borrowrecords/edit";
        }

        borrowRecordService.updateBorrowRecord(updatedRecord.getId(), updatedRecord);
        return "redirect:/borrowrecords";
    }

    // Xóa bản ghi mượn theo ID
    @GetMapping("/delete/{id}")
    public String deleteBorrowRecord(@PathVariable String id) {
        borrowRecordService.deleteBorrowRecordById(id);
        return "redirect:/borrowrecords";
    }
}
