package com.example.appmanifest.repository;

import com.example.appmanifest.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT * FROM Expense e where e.added_at >= :data1 and e.added_at <= :data2", nativeQuery = true)
    List<Expense> findBySelectionDate(Timestamp data1, Timestamp data2);
}
