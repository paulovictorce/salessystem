package com.poliveira.salessystem.productapi.modules.product.repository;

import com.poliveira.salessystem.productapi.modules.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
