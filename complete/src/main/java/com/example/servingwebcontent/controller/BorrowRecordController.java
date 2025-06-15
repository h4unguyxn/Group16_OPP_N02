package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.BorrowRecord;
import com.example.servingwebcontent.service.BorrowRecordServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/borrowrecords")
public class BorrowRecordController {

    private final BorrowRecordServiceWeb borrowRecordService;

    @Autowired
    public BorrowRecordController(BorrowRecordServiceWeb borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping
    public String listBorrowRecords(Model model) {
        model.addAttribute("borrowrecords", borrowRecordService.getAllBorrowRecords());
        return "borrowrecords/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("borrowrecord", new BorrowRecord());
        return "borrowrecords/add";
    }

    @PostMapping("/add")
    public String addBorrowRecord(@ModelAttribute BorrowRecord borrowRecord) {
        if (borrowRecord.getBorrowDate() == null) {
            borrowRecord.setBorrowDate(LocalDate.now());
        }
        borrowRecordService.addBorrowRecord(borrowRecord);
        return "redirect:/borrowrecords";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        BorrowRecord borrowRecord = borrowRecordService.findBorrowRecordById(id);
        if (borrowRecord == null) {
            return "redirect:/borrowrecords";
        }
        model.addAttribute("borrowrecord", borrowRecord);
        return "borrowrecords/edit";
    }

    @PostMapping("/edit")
    public String updateBorrowRecord(@ModelAttribute BorrowRecord borrowRecord) {
        borrowRecordService.updateBorrowRecord(borrowRecord.getId(), borrowRecord);
        return "redirect:/borrowrecords";
    }

    @GetMapping("/delete/{id}")
    public String deleteBorrowRecord(@PathVariable String id) {
        borrowRecordService.deleteBorrowRecordById(id);
        return "redirect:/borrowrecords";
    }
}
