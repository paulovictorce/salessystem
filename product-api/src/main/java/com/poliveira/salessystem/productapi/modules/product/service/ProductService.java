package com.poliveira.salessystem.productapi.modules.product.service;


import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.ValidationException;
import com.poliveira.salessystem.productapi.modules.category.service.CategoryService;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductRequest;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductResponse;
import com.poliveira.salessystem.productapi.modules.product.model.Product;
import com.poliveira.salessystem.productapi.modules.product.repository.ProductRepository;
import com.poliveira.salessystem.productapi.modules.supplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private static final Integer ZERO = 0;
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private SupplierService supplierService;

  @Autowired
  private CategoryService categoryService;

  public ProductResponse save(ProductRequest request) {
    validateProductDataInformed(request);
    validateCategoryAndSupplierInformed(request);
    var category = categoryService.findById(request.getCategoryId());
    var supplier = supplierService.findById(request.getSupplierId());
    var product = productRepository.save(Product.of(request, category, supplier));
    return ProductResponse.of((product));
  }

  private void validateProductDataInformed(ProductRequest request) {
    if (isEmpty(request.getName())) {
      throw new ValidationException("The product's name was not informed.");
    }

    if (isEmpty(request.getQuantityAvailable())) {
      throw new ValidationException("The product's quantity was not informed.");
    }

    if (request.getQuantityAvailable() <= ZERO ) {
      throw new ValidationException("The product's quantity should not be less or equal zero.");
    }
  }

  private void validateCategoryAndSupplierInformed(ProductRequest request) {
    if (isEmpty(request.getCategoryId())) {
      throw new ValidationException("The category id was not informed.");
    }

    if (isEmpty(request.getSupplierId())) {
      throw new ValidationException("The supplier id was not informed.");
    }
  }


}
