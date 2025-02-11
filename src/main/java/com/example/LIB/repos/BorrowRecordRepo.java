package com.example.LIB.repos;

import com.example.LIB.model.BorrowRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BorrowRecordRepo extends MongoRepository<BorrowRecord, String> {

    BorrowRecord findByBookId(String bookId );
    List<BorrowRecord>  findByReturnDateBefore(String Date);

}