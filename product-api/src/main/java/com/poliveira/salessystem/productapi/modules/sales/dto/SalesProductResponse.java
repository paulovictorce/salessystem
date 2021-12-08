package com.poliveira.salessystem.productapi.modules.sales.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesProductResponse {

  private List<String> salesIds;
}
