package com.poliveira.salessystem.productapi.modules.product.repository;

import com.poliveira.salessystem.productapi.modules.product.model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findByNameIgnoreCaseContaining(String name);

  List<Product> findByCategoryId(Integer id);

  List<Product> findBySupplierId(Integer id);

  Boolean existsByCategoryId(Integer id);

  Boolean existsBySupplierId(Integer id);
}
