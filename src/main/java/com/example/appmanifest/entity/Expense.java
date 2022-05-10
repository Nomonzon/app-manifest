package com.example.appmanifest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double sum;

    private String comment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp addedAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updated;

    @ManyToOne
    private Category category;


    public Expense(double sum, String comment, Category category) {
        this.sum = sum;
        this.comment = comment;
        this.category = category;
    }
}
