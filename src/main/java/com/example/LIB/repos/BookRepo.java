package com.example.LIB.repos;
import com.example.LIB.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
public interface BookRepo extends MongoRepository<Book,String> {
    List<Book> findByCategory(String category);
}
