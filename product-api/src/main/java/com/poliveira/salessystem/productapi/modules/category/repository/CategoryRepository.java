package com.poliveira.salessystem.productapi.modules.category.repository;

import com.poliveira.salessystem.productapi.modules.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
