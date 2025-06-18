package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.service.StudentServiceWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentServiceWeb studentService;

    @Autowired
    public StudentController(StudentServiceWeb studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String listStudents(Model model) {
        try {
            model.addAttribute("students", studentService.getAllStudents());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Log hoặc cleanup nếu cần
        }
        return "students/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        try {
            model.addAttribute("student", new Student());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "students/add";
    }

    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        try {
            studentService.addStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        try {
            Student student = studentService.findStudentById(id);
            if (student == null) {
                return "redirect:/students";
            }
            model.addAttribute("student", student);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "students/edit";
    }

    @PostMapping("/edit")
    public String updateStudent(@ModelAttribute Student student) {
        try {
            studentService.updateStudent(student.getId(), student);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        try {
            studentService.deleteStudentById(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return "redirect:/students";
    }
}
