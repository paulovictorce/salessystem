package com.poliveira.salessystem.productapi.modules.product.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductStockDTO {

  private String salesId;
  private List<ProductQuantityDTO> products;
}
