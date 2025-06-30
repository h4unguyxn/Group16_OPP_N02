package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Student;
import com.example.servingwebcontent.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceWeb {

    private final StudentRepository repository;

    public StudentServiceWeb(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        repository.save(student);
    }

    public void updateStudent(String id, Student updatedStudent) {
        if (repository.existsById(id)) {
            repository.save(updatedStudent);
        }
    }

    public void deleteStudentById(String id) {
        repository.deleteById(id);
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student findStudentById(String id) {
        return repository.findById(id).orElse(null);
    }
}
