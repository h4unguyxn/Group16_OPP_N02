package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceWeb studentService;

    @Autowired
    public StudentController(StudentServiceWeb studentService) {
        this.studentService = studentService;
    }

    // Hiển thị danh sách sinh viên
    @GetMapping
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";  // templates/students/list.html
    }

    // Hiển thị form thêm sinh viên mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/add";  // templates/students/add.html
    }

    // Xử lý khi submit form thêm sinh viên
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        studentService.addStudent(student);
        return "redirect:/students";
    }

    // Hiển thị form chỉnh sửa sinh viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Student student = studentService.findStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "students/edit";  // templates/students/edit.html
    }

    // Xử lý khi submit form chỉnh sửa sinh viên
    @PostMapping("/edit")
    public String updateStudent(@ModelAttribute Student student) {
        studentService.updateStudent(student.getId(), student);
        return "redirect:/students";
    }

    // Xóa sinh viên theo ID
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}
