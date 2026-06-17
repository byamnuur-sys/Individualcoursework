
import java.time.LocalDate;

public class Loan {
      private Member member;
      private Book book;
      private LocalDate borrowDate;
      private LocalDate dueDate;
  
      public Loan(Member member, Book book, LocalDate borrowDate, int loanDurationDays) {
          this.member = member;
          this.book = book;
          this.borrowDate = borrowDate;
          this.dueDate = borrowDate.plusDays(loanDurationDays);
      }
  
      // Accessors and Mutators
      public Member getMember() {
          return member;
      }
  
      public Book getBook() {
          return book;
      }
  
      public LocalDate getBorrowDate() {
          return borrowDate;
      }
  
      public LocalDate getDueDate() {
          return dueDate;
      }
  
      @Override
      public String toString() {
          return "Loan Details: [Book: " + book.getTitle() + 
                 " | Borrowed By: " + member.getName() + 
                 " | Borrowed: " + borrowDate + 
                 " | Due: " + dueDate + "]";
      }
  }



