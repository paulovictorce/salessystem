package com.poliveira.salessystem.productapi.modules.product.repository;

import com.poliveira.salessystem.productapi.modules.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
