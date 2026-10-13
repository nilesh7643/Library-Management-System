package com.example.library.model;
import jakarta.persistence.*;
@Entity @Table(name="app_users", uniqueConstraints=@UniqueConstraint(columnNames="username"))
public class AppUser {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false, unique=true) private String username;
 @Column(nullable=false) private String password;
 @Column(nullable=false) private String role;
 @Column(nullable=false) private boolean enabled=true;
 public AppUser() {}
 public AppUser(String username,String password,String role){this.username=username;this.password=password;this.role=role;this.enabled=true;}
 public Long getId(){return id;} public String getUsername(){return username;} public void setUsername(String v){username=v;}
 public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public String getRole(){return role;} public void setRole(String v){role=v;}
 public boolean isEnabled(){return enabled;} public void setEnabled(boolean v){enabled=v;}
}