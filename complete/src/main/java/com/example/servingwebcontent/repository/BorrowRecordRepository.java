package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, String> {
    List<BorrowRecord> findByStudentId(String studentId);
    List<BorrowRecord> findByBookId(String bookId);
}
