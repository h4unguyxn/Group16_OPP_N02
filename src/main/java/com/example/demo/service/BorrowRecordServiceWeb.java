package com.example.demo.service;

import com.example.demo.manager.GenericManager;
import com.example.demo.model.BorrowRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordServiceWeb {
    private final GenericManager<BorrowRecord> manager = new GenericManager<>("borrowrecords.dat");

    public List<BorrowRecord> getAllBorrowRecords() {
        return manager.getAll();
    }

    public void addBorrowRecord(BorrowRecord record) {
        manager.add(record);
    }

    public BorrowRecord findBorrowRecordById(String id) {
        return manager.findById(id);
    }

    public boolean updateBorrowRecord(String id, BorrowRecord updated) {
        return manager.update(id, updated);
    }

    public void deleteBorrowRecordById(String id) {
        manager.delete(id);
    }
}
