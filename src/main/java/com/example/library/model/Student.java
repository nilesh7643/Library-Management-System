package com.example.library.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
@Entity @Table(name="students", uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false) private String name;
 @Email @NotBlank @Column(nullable=false,unique=true) private String email;
 private String phone; private String department; private String course; private Integer semester;
 private boolean active=true;
 public Student(){}
 public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
 public String getDepartment(){return department;} public void setDepartment(String v){department=v;}
 public String getCourse(){return course;} public void setCourse(String v){course=v;}
 public Integer getSemester(){return semester;} public void setSemester(Integer v){semester=v;}
 public boolean isActive(){return active;} public void setActive(boolean v){active=v;}
}