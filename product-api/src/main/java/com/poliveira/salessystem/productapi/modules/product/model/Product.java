package com.poliveira.salessystem.productapi.modules.product.model;

import com.poliveira.salessystem.productapi.modules.category.model.Category;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductRequest;
import com.poliveira.salessystem.productapi.modules.supplier.model.Supplier;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCT")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(name = "FK_CATEGORY", nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "FK_SUPPLIER", nullable = false)
  private Supplier supplier;

  @Column(name = "QUANTITY_AVAILABLE", nullable = false)
  private Integer quantityAvailable;

  @Column(name = "CREATED_AT", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
  }

  public static Product of(ProductRequest request, Category category, Supplier supplier) {
    return Product.builder().name(request.getName()).quantityAvailable(
        request.getQuantityAvailable()).category(category).supplier(supplier).build();
  }

  public void updateStock(Integer quantity) {
    quantityAvailable -= quantity;
  }
}
