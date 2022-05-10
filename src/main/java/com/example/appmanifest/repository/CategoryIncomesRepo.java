package com.example.appmanifest.repository;

import com.example.appmanifest.entity.CategoryIncome;
import com.example.appmanifest.projection.CategoryIncomesCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "categoryIncomes", excerptProjection = CategoryIncomesCustom.class)
public interface CategoryIncomesRepo extends JpaRepository<CategoryIncome, Long> {
}
