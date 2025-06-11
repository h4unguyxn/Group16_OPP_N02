package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceWeb {
    private final GenericManager<Student> manager = new GenericManager<>("students.dat");

    public List<Student> getAllStudents() {
        return manager.getAll();
    }

    public void addStudent(Student student) {
        manager.add(student);
    }

    public Student findStudentById(String id) {
        return manager.findById(id);
    }

    public boolean updateStudent(String id, Student updated) {
        return manager.update(id, updated);
    }

    public void deleteStudentById(String id) {
        manager.delete(id);
    }
}
