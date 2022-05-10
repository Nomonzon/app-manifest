package com.example.appmanifest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeDto {
    private double sum;
    private String comment;
    private Long categoryId;
}
