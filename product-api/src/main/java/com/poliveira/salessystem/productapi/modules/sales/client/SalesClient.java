package com.poliveira.salessystem.productapi.modules.sales.client;

import com.poliveira.salessystem.productapi.modules.sales.dto.SalesProductResponse;
import java.util.Optional;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "salesClient", contextId = "salesClient", url = "${app-config.services.sales}")
public interface SalesClient {

  @GetMapping("products/{productId}")
  Optional<SalesProductResponse> findSalesByProductId(@PathVariable Integer productId);


}
