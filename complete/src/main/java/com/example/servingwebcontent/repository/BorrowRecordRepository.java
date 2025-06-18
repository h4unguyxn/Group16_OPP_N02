package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.BorrowRecord;

public class BorrowRecordRepository extends GenericManager<BorrowRecord> {
    public BorrowRecordRepository() {
        super("borrowrecords.dat");
    }
}
