package com.example.LIB.repos;
import com.example.LIB.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book,String> {
}
