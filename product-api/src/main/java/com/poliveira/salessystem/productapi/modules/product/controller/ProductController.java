package com.poliveira.salessystem.productapi.modules.product.controller;

import com.poliveira.salessystem.productapi.config.response.SuccessResponse;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductCheckStockRequest;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductRequest;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductResponse;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductSalesResponse;
import com.poliveira.salessystem.productapi.modules.product.service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductResponse> findAll() {
    return productService.findAll();
  }

  @GetMapping("{id}")
  public ProductResponse findById(@PathVariable Integer id) {
    return productService.findByIdResponse(id);
  }

  @GetMapping("name/{name}")
  public List<ProductResponse> findByName(@PathVariable String name) {
    return productService.findByName(name);
  }

  @GetMapping("category/{id}")
  public List<ProductResponse> findByCategoryId(@PathVariable Integer id) {
    return productService.findByCategoryId(id);
  }

  @GetMapping("supplier/{id}")
  public List<ProductResponse> findBySupplierId(@PathVariable Integer id) {
    return productService.findBySupplierId(id);
  }

  @PostMapping
  public ProductResponse save(@RequestBody ProductRequest request) {
    return productService.save(request);
  }

  @PutMapping("{id}")
  public ProductResponse update(@RequestBody ProductRequest request, @PathVariable Integer id) {
    return productService.update(request, id);
  }

  @DeleteMapping("{id}")
  public SuccessResponse delete(@PathVariable Integer id) {
    return productService.delete(id);
  }

  @PostMapping("check-stock")
  public SuccessResponse checkProductStock(ProductCheckStockRequest request) {
    return productService.checkProductsStock(request);
  }

  @GetMapping("{id}/sales")
  public ProductSalesResponse findProductsSales(@PathVariable Integer id) {
    return productService.findSales(id);
  }
}
