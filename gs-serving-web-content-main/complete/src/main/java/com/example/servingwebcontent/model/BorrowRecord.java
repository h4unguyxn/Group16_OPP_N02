package com.example.servingwebcontent.model;

import com.example.servingwebcontent.common.Identifiable;
import java.io.Serializable;
import java.time.LocalDate;

public class BorrowRecord implements Serializable, Identifiable {
    private String id;
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;

    public BorrowRecord() {}

    public BorrowRecord(String studentId, String bookId, LocalDate borrowDate) {
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.id = generateId();
    }

    public String generateId() {
        return studentId + "_" + bookId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { 
        this.studentId = studentId;
        this.id = generateId();
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { 
        this.bookId = bookId;
        this.id = generateId();
    }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
}
