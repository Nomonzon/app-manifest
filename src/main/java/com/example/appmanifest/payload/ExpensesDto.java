package com.example.appmanifest.payload;

import com.example.appmanifest.entity.Category;
import lombok.Data;

@Data
public class ExpensesDto {

    private double sum;
    private String comment;
    private Long categoryId;

}
