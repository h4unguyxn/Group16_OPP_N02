package com.example.servingwebcontent.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {

    @Id
    private String id;

    @Column(nullable = false)
    private String studentId;

    @Column(nullable = false)
    private String bookId;

    private LocalDate borrowDate;
    private LocalDate returnDate;

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
        this.id = generateId(); // cập nhật ID nếu thay đổi studentId
    }

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) {
        this.bookId = bookId;
        this.id = generateId(); // cập nhật ID nếu thay đổi bookId
    }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
