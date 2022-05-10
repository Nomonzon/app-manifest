package com.example.appmanifest.projection;

import com.example.appmanifest.entity.CategoryIncome;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = CategoryIncome.class)
public interface CategoryIncomesCustom {
    Long getId();
    String getName();
}
