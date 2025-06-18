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
        try {
            repository.add(student);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void updateStudent(String id, Student updatedStudent) {
        try {
            repository.update(id, updatedStudent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void deleteStudentById(String id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<Student> getAllStudents() {
        try {
            return repository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // trả về danh sách rỗng nếu có lỗi
        } finally {
        }
    }

    public Student findStudentById(String id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // trả về null nếu có lỗi
        } finally {
        }
    }
}
