package com.poliveira.salessystem.productapi.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.poliveira.salessystem.productapi.modules.category.dto.CategoryResponse;
import com.poliveira.salessystem.productapi.modules.product.model.Product;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierResponse;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

  private Integer id;
  private String name;
  private CategoryResponse category;
  private SupplierResponse supplier;
  private Integer quantityAvailable;

  @JsonProperty("created_at")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime createdAt;

  public static ProductResponse of(Product product) {
    return ProductResponse.builder().id(product.getId()).name(product.getName()).quantityAvailable(
        product.getQuantityAvailable()).supplier(SupplierResponse.of(product.getSupplier()))
        .category(CategoryResponse.of(product.getCategory())).createdAt(product.getCreatedAt()).build();
  }
}
