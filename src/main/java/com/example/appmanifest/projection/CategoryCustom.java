package com.example.appmanifest.projection;

import com.example.appmanifest.entity.Category;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Category.class)
public interface CategoryCustom {

    Long getId();

    String getName();
}
