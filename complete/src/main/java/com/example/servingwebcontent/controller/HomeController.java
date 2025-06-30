package com.example.servingwebcontent.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.servingwebcontent.service.BorrowRecordServiceWeb;
import com.example.servingwebcontent.model.BorrowRecord;

@Controller
public class HomeController {

    private final BorrowRecordServiceWeb borrowRecordService;

    public HomeController(BorrowRecordServiceWeb borrowRecordService) {
        this.borrowRecordService = borrowRecordService;
    }

    @GetMapping("/")
    public String showIndex(Model model) {
        try {
            List<BorrowRecord> dueSoonList = borrowRecordService.getBorrowRecordsDueSoon(3);
            model.addAttribute("dueSoonRecords", dueSoonList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }
}

