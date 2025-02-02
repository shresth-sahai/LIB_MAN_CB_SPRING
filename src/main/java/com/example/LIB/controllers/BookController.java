package com.example.LIB.controllers;
import com.example.LIB.model.Book;
import com.example.LIB.repos.BookRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RequestMapping("/books")
public class BookController {
    private final BookRepo bookRepository;

    public BookController(BookRepo bookRepository) {
        this.bookRepository = bookRepository;
    }
@GetMapping
    public String listBooks(Model model){
        List<Book> books=bookRepository.findAll();
        model.addAttribute("books",books);
        return "books";
}
    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return "redirect:/books";
    }
}
