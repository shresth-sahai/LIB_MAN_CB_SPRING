package com.example.LIB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LibApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibApplication.class, args);
	}

}
/*
✅ 1. Book Management
Add, update, and delete books
Categorize books (e.g., Fiction, Non-Fiction, Sci-Fi, Mystery)
Search books by title, author, or category
Sort books by availability, name, or category
✅ 2. User Management
Register new library members
View user details (Name, Email, Membership type)
Edit or delete users
Membership levels (Regular, Premium, VIP)
✅ 3. Borrowing & Return System
Users can borrow books
Set due dates for returns (e.g., 14 days max)
Track overdue books
Send email reminders for due books -> cron jobs
📌 Advanced Features
🚀 4. Authentication & Authorization
User login and registration
Admin panel (Only admins can add/remove books)
Different user roles (Admin, Librarian, Member)
JWT Authentication (Secure login)

📅 5. Reservation System
Reserve books if they’re currently borrowed
Waiting list feature (First-come, first-serve)
Cancel reservation option

🔔 6. Notifications
Email or SMS alerts when books are due
Reminder emails for upcoming return dates

 */
