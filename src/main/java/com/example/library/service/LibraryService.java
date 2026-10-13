package com.example.library.service;
import com.example.library.model.*;
import com.example.library.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class LibraryService {
 private final BookRepository books; private final StudentRepository students; private final BorrowTransactionRepository txs;
 public LibraryService(BookRepository b,StudentRepository s,BorrowTransactionRepository t){books=b;students=s;txs=t;}
 public List<Book> searchBooks(String q){
  if(q==null||q.isBlank())return books.findAll();
  return books.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrIsbnContainingIgnoreCase(q,q,q);
 }
 @Transactional public BorrowTransaction issueBook(Long bookId,Long studentId,int loanDays){
  Book b=books.findById(bookId).orElseThrow(()->new IllegalArgumentException("Book not found"));
  Student s=students.findById(studentId).orElseThrow(()->new IllegalArgumentException("Student not found"));
  if(!s.isActive())throw new IllegalStateException("Student account is inactive.");
  if(loanDays!=7 && loanDays!=30)throw new IllegalArgumentException("Loan period must be 7 or 30 days.");
  if(b.getAvailableQuantity()<1)throw new IllegalStateException("No copies of this book are currently available.");
  b.setAvailableQuantity(b.getAvailableQuantity()-1); books.save(b);
  BorrowTransaction t=new BorrowTransaction();t.setBook(b);t.setStudent(s);t.setIssueDate(LocalDate.now());t.setLoanDays(loanDays);t.setDueDate(LocalDate.now().plusDays(loanDays));t.setStatus("ISSUED");t.setFine(0);
  return txs.save(t);
 }
 @Transactional public BorrowTransaction returnBook(Long id){
  BorrowTransaction t=txs.findById(id).orElseThrow(()->new IllegalArgumentException("Transaction not found"));
  if(!"ISSUED".equals(t.getStatus()))throw new IllegalStateException("This transaction has already been returned.");
  LocalDate today=LocalDate.now();long days=Math.max(0,ChronoUnit.DAYS.between(t.getDueDate(),today));
  t.setReturnDate(today);t.setFine(days*5.0);t.setStatus("RETURNED");
  Book b=t.getBook();b.setAvailableQuantity(b.getAvailableQuantity()+1);books.save(b);
  return txs.save(t);
 }
}