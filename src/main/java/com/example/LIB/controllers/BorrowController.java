package com.example.LIB.controllers;

import com.example.LIB.model.Book;
import com.example.LIB.model.BorrowRecord;
import com.example.LIB.model.User;
import com.example.LIB.repos.BookRepo;
import com.example.LIB.repos.BorrowRecordRepo;
import com.example.LIB.repos.UserRepo;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/borrow")
public class BorrowController {
    private final BookRepo bookrepo;
    private final BorrowRecordRepo borrowRecordRepo;
    private final UserRepo userRepo;

    public BorrowController(BookRepo bookRepo, BorrowRecordRepo borrowRecordRepo,
                            UserRepo userRepo) {
        this.bookrepo = bookRepo;
        this.borrowRecordRepo = borrowRecordRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/borrowBook")
    public String borrowBook(@RequestParam String bookId, @RequestParam String userId) {
        Book book = bookrepo.findById(bookId).orElse(null);
        User user = userRepo.findById(Long.parseLong(userId)).orElse(null);

        if (book == null || user == null || !book.isAvailable()) {
            return "redirect:/books?error=Book Unavaiable or User Not Found";
        }
        LocalDate borrowDate = LocalDate.now();
        LocalDate returnDate = borrowDate.plusDays(14);
        BorrowRecord record = new BorrowRecord(null, bookId, userId, borrowDate.toString(),
                returnDate.toString());
        borrowRecordRepo.save(record);
        book.setAvailable(false);
        bookrepo.save(book);

        return "redirect:/books?success=Book Borrow 200!";
    }

    @PostMapping("/returnBook")
    public String returnBook(@RequestParam String bookId) {
        BorrowRecord record = borrowRecordRepo.findByBookId(bookId);
        if (record == null) {
            return "redirect:/books?error=No active borrow record found";
        }
        Book book = bookrepo.findById(bookId).orElse(null);
        if (book != null) {
            book.setAvailable(true);
            bookrepo.save(book);
        }
        borrowRecordRepo.delete(record);
        return "redirect:/books?success=Book returned!";
    }

    @GetMapping("/overdue")
    public String getOverdueBooks(Model model) {
        LocalDate today = LocalDate.now();
        List<BorrowRecord> overdueRecords = borrowRecordRepo.findByReturnDateBefore(today.toString());
        model.addAttribute("overdueRecords", overdueRecords);
        return "overloads";
    }
    // cron jobs -> its a utility fr scehduling tasks at a certain interval
    // @EnableScehduling ->
    // cron expresssion -> six sequential fields -> second ,min,hr,day of month,month,day of week
    // * * * * * * -> Do always
    // "*/5 * * * * * " -> 5 sec
    // 0 0 0 25 12 * ->
    // */1 * * * * ? -> 1sec
    //0 */1 * * * ? -> 1 min
    // 0 0 */1  * * ? -> 1 hr
    // 0 0 0 */1  *  ?-> every day
    // 0 0 0 1 1  * ->  jan 1st
    // 0 0 8 ? 4 * -> 8 am april every year
    // 0 0 8-11 * * * -> 8 am to 11 am every day
    // 0 0 9 14 2 Sun,Tue
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendOverdueReminders(){
        LocalDate today=LocalDate.now();
     List<BorrowRecord> overdueRecords=borrowRecordRepo.findByReturnDateBefore(today.toString());
     for(BorrowRecord record:overdueRecords){
         User user=userRepo.findById(Long.parseLong(record.getUserId())).orElse(null);
         if(user!=null){
             System.out.print("Sending email reminder!");

             // implement email sender api
         }
     }

    }

}
