package com.poliveira.salessystem.productapi.modules.product.repository;

import com.poliveira.salessystem.productapi.modules.product.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
