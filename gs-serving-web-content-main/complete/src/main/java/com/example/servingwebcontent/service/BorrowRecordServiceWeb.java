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
        try {
            repository.add(record);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void updateBorrowRecord(String id, BorrowRecord updatedRecord) {
        try {
            repository.update(id, updatedRecord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void deleteBorrowRecordById(String id) {
        try {
            repository.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        try {
            return repository.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        } finally {
        }
    }

    public BorrowRecord findBorrowRecordById(String id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }
}
