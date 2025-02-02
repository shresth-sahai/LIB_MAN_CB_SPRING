package com.example.LIB.repos;

import com.example.LIB.model.BorrowRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BorrowRecordRepo extends MongoRepository<BorrowRecord,String> {
}
