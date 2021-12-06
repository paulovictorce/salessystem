package com.poliveira.salessystem.productapi.modules.product.service;


import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.ValidationException;
import com.poliveira.salessystem.productapi.config.response.SuccessResponse;
import com.poliveira.salessystem.productapi.modules.category.service.CategoryService;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductRequest;
import com.poliveira.salessystem.productapi.modules.product.dto.ProductResponse;
import com.poliveira.salessystem.productapi.modules.product.model.Product;
import com.poliveira.salessystem.productapi.modules.product.repository.ProductRepository;
import com.poliveira.salessystem.productapi.modules.supplier.service.SupplierService;
import java.util.List;
import java.util.stream.Collectors;
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

  public ProductResponse findByIdResponse(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The product id must be informed.");
    }
    return ProductResponse.of(productRepository.findById(id)
        .orElseThrow(() -> new ValidationException("There's no product for the given ID.")));
  }

  public List<ProductResponse> findAll() {
    return productRepository.findAll().stream().map(ProductResponse::of)
        .collect(
            Collectors.toList());
  }

  public List<ProductResponse> findByName(String name) {
    if (isEmpty(name)) {
      throw new ValidationException("The product name must be informed.");
    }
    return productRepository.findByNameIgnoreCaseContaining(name).stream()
        .map(ProductResponse::of)
        .collect(
            Collectors.toList());
  }

  public List<ProductResponse> findByCategoryId(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The product categoryId must be informed.");
    }
    return productRepository.findByCategoryId(id).stream()
        .map(ProductResponse::of)
        .collect(
            Collectors.toList());
  }

  public List<ProductResponse> findBySupplierId(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The product supplierId must be informed.");
    }
    return productRepository.findBySupplierId(id).stream()
        .map(ProductResponse::of)
        .collect(
            Collectors.toList());
  }

  public ProductResponse save(ProductRequest request) {
    validateProductDataInformed(request);
    validateCategoryAndSupplierInformed(request);
    var category = categoryService.findById(request.getCategoryId());
    var supplier = supplierService.findById(request.getSupplierId());
    var product = productRepository.save(Product.of(request, category, supplier));
    return ProductResponse.of((product));
  }

  public ProductResponse update(ProductRequest request, Integer id) {
    validateProductDataInformed(request);
    validateCategoryAndSupplierInformed(request);
    validateInformedId(id);
    var category = categoryService.findById(request.getCategoryId());
    var supplier = supplierService.findById(request.getSupplierId());
    var product = Product.of(request, category, supplier);
    product.setId(id);
    productRepository.save(product);
    return ProductResponse.of((product));
  }

  public SuccessResponse delete(Integer id) {
    validateInformedId(id);
    productRepository.deleteById(id);
    return SuccessResponse.create("The product was deleted.");
  }

  public Boolean existsByCategoryId(Integer id) {
    return productRepository.existsByCategoryId(id);
  }

  public Boolean existsBySupplierId(Integer id) {
    return productRepository.existsBySupplierId(id);
  }

  private void validateProductDataInformed(ProductRequest request) {
    if (isEmpty(request.getName())) {
      throw new ValidationException("The product's name was not informed.");
    }

    if (isEmpty(request.getQuantityAvailable())) {
      throw new ValidationException("The product's quantity was not informed.");
    }

    if (request.getQuantityAvailable() <= ZERO) {
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

  private void validateInformedId(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The category id was not informed.");
    }
  }


}
