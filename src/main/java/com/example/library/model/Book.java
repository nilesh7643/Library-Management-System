package com.example.library.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
@Entity @Table(name="books", uniqueConstraints=@UniqueConstraint(columnNames="isbn"))
public class Book {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false,unique=true) private String isbn;
 @NotBlank @Column(nullable=false) private String title;
 @NotBlank @Column(nullable=false) private String author;
 private String category; private String publisher; private String coverImage; private Integer publicationYear; private String shelfNumber;
 @Column(length=1200) private String description;
 @Min(0) private int quantity=1;
 @Min(0) private int availableQuantity=1;
 public Book(){}
 public Long getId(){return id;} public String getIsbn(){return isbn;} public void setIsbn(String v){isbn=v;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getAuthor(){return author;} public void setAuthor(String v){author=v;}
 public String getCategory(){return category;} public void setCategory(String v){category=v;}
 public String getPublisher(){return publisher;} public void setPublisher(String v){publisher=v;}
 public String getCoverImage(){return coverImage;} public void setCoverImage(String v){coverImage=v;}
 public Integer getPublicationYear(){return publicationYear;} public void setPublicationYear(Integer v){publicationYear=v;}
 public String getShelfNumber(){return shelfNumber;} public void setShelfNumber(String v){shelfNumber=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public int getQuantity(){return quantity;} public void setQuantity(int v){quantity=v;}
 public int getAvailableQuantity(){return availableQuantity;} public void setAvailableQuantity(int v){availableQuantity=v;}
}