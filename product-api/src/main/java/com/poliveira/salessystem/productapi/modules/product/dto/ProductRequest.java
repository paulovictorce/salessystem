package com.poliveira.salessystem.productapi.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProductRequest {

  private String name;
  private Integer categoryId;
  private Integer supplierId;

  @JsonProperty("quantity_available")
  private Integer quantityAvailable;

  @JsonProperty("created_at")
  @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
  private LocalDateTime createdAt;
}
