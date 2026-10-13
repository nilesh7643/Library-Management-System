package com.example.library.config;
import com.example.library.model.*;
import com.example.library.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class DataInitializer {
 @Bean CommandLineRunner seedData(AppUserRepository users, BookRepository books, StudentRepository students, PasswordEncoder encoder){
  return args -> {
   if(users.count()==0){ users.save(new AppUser("admin",encoder.encode("Admin@123"),"ADMIN")); users.save(new AppUser("student",encoder.encode("Student@123"),"STUDENT")); }
   if(students.count()==0){ Student s=new Student(); s.setName("Demo Student"); s.setEmail("student@example.com"); s.setDepartment("Computer Science"); s.setCourse("BTech"); s.setSemester(3); s.setPhone("9000000000"); students.save(s); }
   seedBooks(books);
  };
 }
 private void seedBooks(BookRepository repo){
  add(repo,"9780132350884","Clean Code","Robert C. Martin","Programming",5,"A practical guide to writing readable software.","/images/books/clean-code.svg");
  add(repo,"9780131103627","The C Programming Language","Brian W. Kernighan","Programming",4,"Classic introduction to C.","/images/books/c-programming.svg");
  add(repo,"9780262033848","Introduction to Algorithms","Thomas H. Cormen","Algorithms",3,"Comprehensive algorithms textbook.","/images/books/algorithms.svg");
  add(repo,"9780134685991","Effective Java","Joshua Bloch","Java",4,"Best practices for Java programming.","/images/books/effective-java.svg");
  add(repo,"9781492056270","Designing Data-Intensive Applications","Martin Kleppmann","Systems",2,"Data systems concepts and tradeoffs.","/images/books/data-intensive.svg");
  add(repo,"9780134494166","Effective Modern C++","Scott Meyers","C++",3,"Modern C++ techniques and best practices.","/images/books/modern-cpp.svg");
  add(repo,"9780135957059","The Pragmatic Programmer","David Thomas & Andrew Hunt","Programming",4,"Classic software craftsmanship principles.","/images/books/pragmatic-programmer.svg");
  add(repo,"9781617294945","Spring in Action","Craig Walls","Spring Boot",3,"Building modern Java applications with Spring.","/images/books/spring-in-action.svg");
  add(repo,"9780735619678","Clean Architecture","Robert C. Martin","Software Design",3,"Architecture and design principles for software.","/images/books/clean-architecture.svg");
  add(repo,"9780134757599","Refactoring","Martin Fowler","Software Design",3,"Improving the design of existing code.","/images/books/refactoring.svg");
  add(repo,"9781491950357","Head First Design Patterns","Eric Freeman & Elisabeth Robson","Design Patterns",3,"Visual introduction to reusable software designs.","/images/books/design-patterns.svg");
  add(repo,"9780132129377","Computer Networks","Andrew S. Tanenbaum","Networking",4,"Foundations of computer networking.","/images/books/computer-networks.svg");
  add(repo,"9780073523323","Data Communications and Networking","Behrouz A. Forouzan","Networking",4,"Data communication and networking fundamentals.","/images/books/data-communications.svg");
  add(repo,"9780133594140","Operating System Concepts","Abraham Silberschatz","Operating Systems",4,"Core concepts of operating systems.","/images/books/os-concepts.svg");
  add(repo,"9780134093413","Modern Operating Systems","Andrew S. Tanenbaum","Operating Systems",3,"Modern OS architecture and implementation concepts.","/images/books/modern-os.svg");
  add(repo,"9780135166307","Database System Concepts","Abraham Silberschatz","Database",4,"Fundamentals of database systems.","/images/books/database-concepts.svg");
  add(repo,"9780321751041","Database Design for Mere Mortals","Michael J. Hernandez","Database",3,"Practical relational database design.","/images/books/database-design.svg");
  add(repo,"9780134448237","Artificial Intelligence: A Modern Approach","Stuart Russell & Peter Norvig","AI",3,"Comprehensive introduction to artificial intelligence.","/images/books/ai-modern-approach.svg");
  add(repo,"9780262046305","Deep Learning","Ian Goodfellow","Machine Learning",3,"Foundations and methods of deep learning.","/images/books/deep-learning.svg");
  add(repo,"9781492032649","Hands-On Machine Learning","Aurélien Géron","Machine Learning",3,"Practical machine learning with Python.","/images/books/hands-on-ml.svg");
  add(repo,"9781098104030","Designing Machine Learning Systems","Chip Huyen","Machine Learning",2,"Production-grade machine learning systems.","/images/books/ml-systems.svg");
  add(repo,"9780135992278","Java: The Complete Reference","Herbert Schildt","Java",4,"Broad reference for Java programming.","/images/books/java-reference.svg");
  add(repo,"9780134687476","Core Java Volume I","Cay S. Horstmann","Java",3,"Core Java language and library concepts.","/images/books/core-java.svg");
  add(repo,"9780132145376","Core Java Volume II","Cay S. Horstmann","Java",3,"Advanced Java programming concepts.","/images/books/core-java-2.svg");
  add(repo,"9780596009205","Head First Java","Kathy Sierra & Bert Bates","Java",4,"Beginner-friendly Java learning guide.","/images/books/head-first-java.svg");
  add(repo,"9780134787961","Java: An Introduction to Problem Solving","Walter Savitch","Java",3,"Java programming and problem solving.","/images/books/java-problem-solving.svg");
  add(repo,"9780134177304","Python Crash Course","Eric Matthes","Python",4,"Hands-on introduction to Python.","/images/books/python-crash-course.svg");
  add(repo,"9781492051367","Fluent Python","Luciano Ramalho","Python",3,"Advanced Python techniques and idioms.","/images/books/fluent-python.svg");
  add(repo,"9781449355739","Learning Python","Mark Lutz","Python",3,"Comprehensive Python programming reference.","/images/books/learning-python.svg");
  add(repo,"9781492051299","Automate the Boring Stuff with Python","Al Sweigart","Python",4,"Useful automation projects with Python.","/images/books/automate-python.svg");
  add(repo,"9781593279288","Python for Everybody","Charles Severance","Python",4,"Beginner-friendly Python and data handling.","/images/books/python-everybody.svg");
  add(repo,"9781492078005","HTML and CSS: Design and Build Websites","Jon Duckett","Web Development",3,"Visual guide to modern web fundamentals.","/images/books/html-css.svg");
  add(repo,"9781491950296","JavaScript: The Definitive Guide","David Flanagan","Web Development",3,"Reference for modern JavaScript.","/images/books/javascript.svg");
  add(repo,"9781617297571","Learning React","Alex Banks & Eve Porcello","Web Development",3,"Practical React development.","/images/books/learning-react.svg");
  add(repo,"9781492056812","Full Stack JavaScript","Azat Mardan","Web Development",2,"Building full-stack web applications.","/images/books/full-stack-js.svg");
  add(repo,"9780321125217","Domain-Driven Design","Eric Evans","Software Design",2,"Domain modeling for complex software.","/images/books/ddd.svg");
 }
 private void add(BookRepository repo,String isbn,String title,String author,String category,int qty,String desc,String image){
  Book b=repo.findByIsbn(isbn).orElseGet(Book::new);
  boolean isNew=b.getId()==null;
  b.setIsbn(isbn); b.setTitle(title); b.setAuthor(author); b.setCategory(category); b.setQuantity(isNew?qty:Math.max(b.getQuantity(),qty)); if(isNew)b.setAvailableQuantity(qty);
  b.setDescription(desc); b.setCoverImage(image); repo.save(b);
 }
}
