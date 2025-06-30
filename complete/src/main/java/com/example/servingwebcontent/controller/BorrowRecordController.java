package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.BorrowRecord;
import com.example.servingwebcontent.service.BorrowRecordServiceWeb;
import com.example.servingwebcontent.service.BookServiceWeb;
import com.example.servingwebcontent.service.StudentServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/borrowrecords")
public class BorrowRecordController {

    private final BorrowRecordServiceWeb borrowRecordService;
    private final BookServiceWeb bookService;
    private final StudentServiceWeb studentService;

    @Autowired
    public BorrowRecordController(BorrowRecordServiceWeb borrowRecordService,
                                   BookServiceWeb bookService,
                                   StudentServiceWeb studentService) {
        this.borrowRecordService = borrowRecordService;
        this.bookService = bookService;
        this.studentService = studentService;
    }

    @GetMapping
    public String listBorrowRecords(Model model) {
        try {
            List<BorrowRecord> records = borrowRecordService.getAllBorrowRecords();
            model.addAttribute("borrowrecords", records);
            return "borrowrecords/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Không thể tải danh sách phiếu mượn.");
            return "error";
        } finally {
        }
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            model.addAttribute("borrowrecord", new BorrowRecord());
            return "borrowrecords/add";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
        }
    }

    @PostMapping("/add")
    public String addBorrowRecord(@ModelAttribute("borrowrecord") BorrowRecord borrowRecord, Model model) {
        try {
            if (studentService.findStudentById(borrowRecord.getStudentId()) == null ||
                bookService.findBookById(borrowRecord.getBookId()) == null) {
                model.addAttribute("error", "Mã sinh viên hoặc mã sách không tồn tại.");
                return "borrowrecords/add";
            }

            if (borrowRecord.getBorrowDate() == null) {
                borrowRecord.setBorrowDate(LocalDate.now());
            }

            borrowRecordService.addBorrowRecord(borrowRecord);
            return "redirect:/borrowrecords";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi thêm phiếu mượn.");
            return "borrowrecords/add";
        } finally {
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            BorrowRecord record = borrowRecordService.findBorrowRecordById(id);
            if (record == null) {
                return "redirect:/borrowrecords";
            }
            model.addAttribute("borrowrecord", record);
            return "borrowrecords/edit";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
        }
    }

    @PostMapping("/edit")
    public String updateBorrowRecord(@ModelAttribute("borrowrecord") BorrowRecord borrowRecord, Model model) {
        try {
            borrowRecordService.updateBorrowRecord(borrowRecord.getId(), borrowRecord);
            return "redirect:/borrowrecords";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi cập nhật phiếu mượn.");
            return "borrowrecords/edit";
        } finally {
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrowRecord(@PathVariable String id) {
        try {
            borrowRecordService.deleteBorrowRecordById(id);
            return "redirect:/borrowrecords";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        } finally {
        }
    }

    @GetMapping("/filter")
    public String filterBorrowRecords(@RequestParam(required = false) String studentId,
                                      @RequestParam(required = false) String bookId,
                                      Model model) {
        try {
            List<BorrowRecord> filteredRecords = borrowRecordService.filterBorrowRecords(studentId, bookId);
            model.addAttribute("borrowrecords", filteredRecords);
            return "borrowrecords/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi lọc phiếu mượn.");
            return "error";
        } finally {
        }
    }
}
