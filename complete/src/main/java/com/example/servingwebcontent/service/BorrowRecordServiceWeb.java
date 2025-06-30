package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.BorrowRecord;
import com.example.servingwebcontent.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowRecordServiceWeb {

    private final BorrowRecordRepository repository;

    public BorrowRecordServiceWeb(BorrowRecordRepository repository) {
        this.repository = repository;
    }

    public void addBorrowRecord(BorrowRecord record) {
        repository.save(record);
    }

    public void updateBorrowRecord(String id, BorrowRecord updatedRecord) {
        if (repository.existsById(id)) {
            repository.save(updatedRecord);
        }
    }

    public void deleteBorrowRecordById(String id) {
        repository.deleteById(id);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return repository.findAll();
    }

    public BorrowRecord findBorrowRecordById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<BorrowRecord> getBorrowRecordsDueSoon(int daysBeforeDue) {
        LocalDate now = LocalDate.now();
        LocalDate deadline = now.plusDays(daysBeforeDue);
        return repository.findAll().stream()
                .filter(record -> {
                    LocalDate returnDate = record.getReturnDate();
                    return returnDate != null &&
                           !returnDate.isBefore(now) &&
                           !returnDate.isAfter(deadline);
                })
                .toList();
    }

    public List<BorrowRecord> filterBorrowRecords(String studentId, String bookId) {
        return repository.findAll().stream()
                .filter(record -> studentId == null || studentId.isBlank() || record.getStudentId().contains(studentId))
                .filter(record -> bookId == null || bookId.isBlank() || record.getBookId().contains(bookId))
                .toList();
    }
}
