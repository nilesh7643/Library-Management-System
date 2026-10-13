package com.example.library.repository;
import com.example.library.model.BorrowTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BorrowTransactionRepository extends JpaRepository<BorrowTransaction,Long>{
 List<BorrowTransaction> findByStatus(String status);
 List<BorrowTransaction> findByStudentIdOrderByIssueDateDesc(Long studentId);
}