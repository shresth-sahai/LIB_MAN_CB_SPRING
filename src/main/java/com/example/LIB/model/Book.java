package com.example.LIB.model;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="books")
public class Book {

    @Id
    private String id;
    private String title;
    private String author; private String category;
    private boolean available=true;

    public Book(){

    }
    public Book(String id, String title, String author,String category, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author; this.category=category;
        this.available = available;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
