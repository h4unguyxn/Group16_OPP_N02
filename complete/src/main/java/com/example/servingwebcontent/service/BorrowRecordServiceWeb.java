package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.BorrowRecord;
import com.example.servingwebcontent.repository.BorrowRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordServiceWeb {

    private final BorrowRecordRepository repository;

    public BorrowRecordServiceWeb() {
        this.repository = new BorrowRecordRepository();
    }

    public void addBorrowRecord(BorrowRecord record) {
        repository.add(record);
    }

    public void updateBorrowRecord(String id, BorrowRecord updatedRecord) {
        repository.update(id, updatedRecord);
    }

    public void deleteBorrowRecordById(String id) {
        repository.delete(id);
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return repository.getAll();
    }

    public BorrowRecord findBorrowRecordById(String id) {
        return repository.findById(id);
    }
}
