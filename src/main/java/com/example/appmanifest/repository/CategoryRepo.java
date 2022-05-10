package com.example.appmanifest.repository;


import com.example.appmanifest.entity.Category;
import com.example.appmanifest.projection.CategoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(path = "category",  excerptProjection = CategoryCustom.class)
public interface CategoryRepo extends JpaRepository<Category, Long > {
}
