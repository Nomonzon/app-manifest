package com.example.appmanifest.repository;

import com.example.appmanifest.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface IncomeRepo extends JpaRepository<Income, Long> {

    @Query(value = "SELECT * FROM Income e where e.added_at >= :data1 and e.added_at <= :data2", nativeQuery = true)
    List<Income> findBySelectionDate(Timestamp data1, Timestamp data2);

}
