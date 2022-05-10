/**
 * This table for category incomes
 * Bu table kirim lar ni qategoriya si uchun
 */
package com.example.appmanifest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Income {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double sum;

    private String comment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp addedAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    private CategoryIncome categoryIncomes;

    public Income(Double sum, String comment, CategoryIncome categoryIncomes) {
        this.sum = sum;
        this.comment = comment;
        this.categoryIncomes = categoryIncomes;
    }
}
