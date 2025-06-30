package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    // bạn có thể thêm phương thức tìm kiếm tùy ý, ví dụ:
    Student findByEmail(String email);
}
