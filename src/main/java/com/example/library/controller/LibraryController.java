package com.example.library.controller;
import com.example.library.model.*;
import com.example.library.repository.*;
import com.example.library.service.LibraryService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class LibraryController {
 private final BookRepository books; private final StudentRepository students; private final BorrowTransactionRepository txs; private final LibraryService service;
 public LibraryController(BookRepository b,StudentRepository s,BorrowTransactionRepository t,LibraryService svc){books=b;students=s;txs=t;service=svc;}
 @GetMapping("/") public String dashboard(Model m,Authentication auth){
  m.addAttribute("bookCount",books.count());m.addAttribute("studentCount",students.count());
  m.addAttribute("availableCount",books.findAll().stream().mapToInt(Book::getAvailableQuantity).sum());
  m.addAttribute("issuedCount",txs.findByStatus("ISSUED").size());m.addAttribute("books",books.findAll());m.addAttribute("username",auth.getName());
  m.addAttribute("isAdmin",auth.getAuthorities().stream().anyMatch(a->a.getAuthority().equals("ROLE_ADMIN")));
  return "dashboard";
 }
 @GetMapping("/books") public String bookList(@RequestParam(required=false) String q,Model m){m.addAttribute("books",service.searchBooks(q));m.addAttribute("q",q==null?"":q);return "books";}
 @GetMapping("/admin/books/new") public String newBook(Model m){m.addAttribute("book",new Book());m.addAttribute("formTitle","Add Book");return "book-form";}
 @GetMapping("/admin/books/edit/{id}") public String editBook(@PathVariable Long id,Model m){m.addAttribute("book",books.findById(id).orElseThrow());m.addAttribute("formTitle","Edit Book");return "book-form";}
 @PostMapping("/admin/books/save") public String saveBook(@Valid @ModelAttribute Book book,BindingResult result,Model m,RedirectAttributes ra){
  if(result.hasErrors()){m.addAttribute("formTitle",book.getId()==null?"Add Book":"Edit Book");return "book-form";}
  if(book.getId()==null)book.setAvailableQuantity(book.getQuantity());
  else {Book old=books.findById(book.getId()).orElseThrow();int borrowed=old.getQuantity()-old.getAvailableQuantity();book.setAvailableQuantity(Math.max(0,book.getQuantity()-borrowed));}
  books.save(book);ra.addFlashAttribute("message","Book saved successfully.");return "redirect:/books";
 }
 @PostMapping("/admin/books/delete/{id}") public String deleteBook(@PathVariable Long id,RedirectAttributes ra){
  Book b=books.findById(id).orElseThrow();
  if(b.getQuantity()!=b.getAvailableQuantity()){ra.addFlashAttribute("error","Cannot delete a book while copies are issued.");return "redirect:/books";}
  books.delete(b);ra.addFlashAttribute("message","Book deleted.");return "redirect:/books";
 }
 @GetMapping("/students") public String studentList(Model m){m.addAttribute("students",students.findAll());return "students";}
 @GetMapping("/admin/students/new") public String newStudent(Model m){m.addAttribute("student",new Student());return "student-form";}
 @PostMapping("/admin/students/save") public String saveStudent(@Valid @ModelAttribute Student student,BindingResult result,Model m,RedirectAttributes ra){
  if(result.hasErrors())return "student-form";students.save(student);ra.addFlashAttribute("message","Student saved successfully.");return "redirect:/students";
 }
 @GetMapping("/issue") public String issuePage(Model m){m.addAttribute("books",books.findAll());m.addAttribute("students",students.findAll());m.addAttribute("loanDays",7);return "issue";}
 @PostMapping("/issue") public String issue(@RequestParam Long bookId,@RequestParam Long studentId,@RequestParam int loanDays,RedirectAttributes ra){
  try{service.issueBook(bookId,studentId,loanDays);ra.addFlashAttribute("message","Book issued successfully for " + loanDays + " days.");}
  catch(RuntimeException e){ra.addFlashAttribute("error",e.getMessage());}return "redirect:/transactions";
 }
 @GetMapping("/transactions") public String transactions(Model m){m.addAttribute("transactions",txs.findAll());return "transactions";}
 @PostMapping("/return/{id}") public String returnBook(@PathVariable Long id,RedirectAttributes ra){
  try{BorrowTransaction t=service.returnBook(id);ra.addFlashAttribute("message","Book returned. Fine calculated: ₹"+t.getFine());}
  catch(RuntimeException e){ra.addFlashAttribute("error",e.getMessage());}return "redirect:/transactions";
 }
}