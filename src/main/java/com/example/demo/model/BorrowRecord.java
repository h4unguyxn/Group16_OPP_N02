package com.example.demo.model;

import com.example.demo.common.Identifiable;
import java.io.Serializable;
import java.time.LocalDate;

public class BorrowRecord implements Serializable, Identifiable {
    private String id;
    private String studentId;
    private String bookId;
    private LocalDate borrowDate;

    public BorrowRecord(String id, String studentId, String bookId, LocalDate borrowDate) {
        this.id = id;
        this.studentId = studentId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
    }

    public String getId() { return studentId + "_" + bookId; } // Unique ID

    public String getStudentId() { return studentId; }
    public String getBookId() { return bookId; }
    public LocalDate getBorrowDate() { return borrowDate; }
}
