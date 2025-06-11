package com.example.demo.model;

import com.example.demo.common.Identifiable;
import java.io.Serializable;
import java.time.LocalDate;

public class BorrowRecord implements Serializable, Identifiable {
    private String id;
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;

    public BorrowRecord() {}

    public BorrowRecord(String id, String studentId, String bookId, LocalDate borrowDate) {
        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
    }
    private String generateId() {
        return studentId + "_" + bookId;
    }

    public String getId() { return studentId + "_" + bookId; } // Unique ID
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { 
        this.studentId = studentId;
        this.id = generateId(); // cập nhật id khi thay đổi studentId
    }
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { 
        this.bookId = bookId;
        this.id = generateId(); // cập nhật id khi thay đổi bookId
    }
    
    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
}
