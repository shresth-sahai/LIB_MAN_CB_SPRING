package com.example.LIB.controllers;

import com.example.LIB.repos.BookRepo;
import com.example.LIB.repos.BorrowRecordRepo;
import com.example.LIB.repos.UserRepo;
import com.sendgrid.SendGrid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;

@Controller
@RequestMapping("/borrow")
public class BorrowSendGridController {
    private final BookRepo bookrepo;
    private final BorrowRecordRepo borrowRecordRepo;
    private final UserRepo userRepo;
    private static  final String SENDGRID_API_KEY="your_sendgrid_api_key";

    public BorrowSendGridController(BookRepo bookRepo, BorrowRecordRepo borrowRecordRepo,
                            UserRepo userRepo) {
        this.bookrepo = bookRepo;
        this.borrowRecordRepo = borrowRecordRepo;
        this.userRepo = userRepo;
    }
    private void sendEmail(String to,String subject,String body){
        Email from=new Email("lib@gmail.com");
        Email toEmail=new Email(to);
        Content content=new Content("text/plain",body);
        Mail mail =new Mail(from,subject,toEmail,content);
        SendGrid sg=new SendGrid(SENDGRID_API_KEY);
        Request request =new Request();
        try{
            request.setMethod(METHOD.POST);
            request.setEndPoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
        sendEmail("lib@gmail.com","Book Cnfm Retunred","HJSBhjdvjdjbjsdjl");
        return "redirect:/books?success=Book returned!";
    }
}
