package com.poliveira.salessystem.productapi.modules.product.rabbit;

import com.poliveira.salessystem.productapi.modules.product.dto.ProductStockDTO;
import com.poliveira.salessystem.productapi.modules.product.service.ProductService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductStockListener {

  @Autowired
  ProductService productService;

  @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
  public void receiveProductStockMessage(ProductStockDTO productStockDTO) {
    productService.updateProductStock(productStockDTO);
  }

}
