package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceWeb {

    private final StudentRepository repository;

    public StudentServiceWeb() {
        this.repository = new StudentRepository();
    }

    public void addStudent(Student student) {
        repository.add(student);
    }

    public void updateStudent(String id, Student updatedStudent) {
        repository.update(id, updatedStudent);
    }

    public void deleteStudentById(String id) {
        repository.delete(id);
    }

    public List<Student> getAllStudents() {
        return repository.getAll();
    }

    public Student findStudentById(String id) {
        return repository.findById(id);
    }
}
