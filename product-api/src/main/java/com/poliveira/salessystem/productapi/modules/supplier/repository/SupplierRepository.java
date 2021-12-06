package com.poliveira.salessystem.productapi.modules.supplier.repository;

import com.poliveira.salessystem.productapi.modules.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

}
