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

    // Hiển thị danh sách phiếu mượn
    @GetMapping
    public String listBorrowRecords(Model model) {
        try {
            model.addAttribute("borrowrecords", borrowRecordService.getAllBorrowRecords());
            System.out.println(">>> Đang hiển thị danh sách phiếu mượn:");
            borrowRecordService.getAllBorrowRecords().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Không thể tải danh sách phiếu mượn.");
        } finally {
        }
        return "borrowrecords/list";
    }

    // Hiển thị form thêm
    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            model.addAttribute("borrowrecord", new BorrowRecord());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "borrowrecords/add";
    }

    // Xử lý form thêm
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
            System.out.println(">>> Đã thêm phiếu mượn: " + borrowRecord);
            return "redirect:/borrowrecords";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Lỗi khi thêm phiếu mượn.");
            return "borrowrecords/add";
        } finally {
        }
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            BorrowRecord borrowRecord = borrowRecordService.findBorrowRecordById(id);
            if (borrowRecord == null) {
                return "redirect:/borrowrecords";
            }
            model.addAttribute("borrowrecord", borrowRecord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "borrowrecords/edit";
    }

    // Xử lý chỉnh sửa
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

    // Xóa phiếu mượn
    @GetMapping("/delete/{id}")
    public String deleteBorrowRecord(@PathVariable String id) {
        try {
            borrowRecordService.deleteBorrowRecordById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "redirect:/borrowrecords";
    }
}
