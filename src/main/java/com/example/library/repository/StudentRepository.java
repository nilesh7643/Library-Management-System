package com.example.library.repository;
import com.example.library.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student,Long>{ Optional<Student> findByEmail(String email); }