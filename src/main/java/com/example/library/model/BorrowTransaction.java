package com.example.library.model;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
@Entity @Table(name="borrow_transactions")
public class BorrowTransaction {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) private Book book;
 @ManyToOne(optional=false) private Student student;
 private LocalDate issueDate; private LocalDate dueDate; private LocalDate returnDate;
 private int loanDays=14; private double fine; private String status="ISSUED";
 public BorrowTransaction(){}
 public Long getId(){return id;} public Book getBook(){return book;} public void setBook(Book v){book=v;}
 public Student getStudent(){return student;} public void setStudent(Student v){student=v;}
 public LocalDate getIssueDate(){return issueDate;} public void setIssueDate(LocalDate v){issueDate=v;}
 public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;}
 public LocalDate getReturnDate(){return returnDate;} public void setReturnDate(LocalDate v){returnDate=v;}
 public int getLoanDays(){return loanDays;} public void setLoanDays(int v){loanDays=v;}
 public double getFine(){return fine;} public void setFine(double v){fine=v;}
 @Transient public long getOverdueDays(){ if(dueDate==null) return 0; return Math.max(0,ChronoUnit.DAYS.between(dueDate, LocalDate.now())); }
 @Transient public double getAccruedFine(){ return getOverdueDays()*5.0; }
 public String getStatus(){return status;} public void setStatus(String v){status=v;}
}