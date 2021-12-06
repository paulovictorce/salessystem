package com.poliveira.salessystem.productapi.modules.category.service;


import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.ValidationException;
import com.poliveira.salessystem.productapi.config.response.SuccessResponse;
import com.poliveira.salessystem.productapi.modules.category.dto.CategoryRequest;
import com.poliveira.salessystem.productapi.modules.category.dto.CategoryResponse;
import com.poliveira.salessystem.productapi.modules.category.model.Category;
import com.poliveira.salessystem.productapi.modules.category.repository.CategoryRepository;
import com.poliveira.salessystem.productapi.modules.product.service.ProductService;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierRequest;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierResponse;
import com.poliveira.salessystem.productapi.modules.supplier.model.Supplier;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private ProductService productService;

  public Category findById(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The category id must be informed.");
    }
    return categoryRepository.findById(id)
        .orElseThrow(() -> new ValidationException("There's no category for the given ID."));
  }

  public CategoryResponse findByIdResponse(Integer id) {
    return CategoryResponse.of(findById(id));
  }

  public List<CategoryResponse> findAll() {
    return categoryRepository.findAll().stream().map(CategoryResponse::of)
        .collect(
            Collectors.toList());
  }

  public List<CategoryResponse> findByDescription(String description) {
    if (isEmpty(description)) {
      throw new ValidationException("The category description must be informed.");
    }
    return categoryRepository.findByDescriptionIgnoreCaseContaining(description).stream()
        .map(CategoryResponse::of)
        .collect(
            Collectors.toList());
  }

  public CategoryResponse save(CategoryRequest request) {
    validateCategoryNameInformed(request);
    var category = categoryRepository.save(Category.of(request));
    return CategoryResponse.of((category));
  }

  public CategoryResponse update(CategoryRequest request, Integer id) {
    validateCategoryNameInformed(request);
    validateInformedId(id);
    var category = Category.of(request);
    category.setId(id);
    categoryRepository.save(category);
    return CategoryResponse.of((category));
  }

  public SuccessResponse delete(Integer id) {
    validateInformedId(id);
    if (productService.existsByCategoryId(id)) {
      throw new ValidationException(
          "You cannot delete this category because it's already defined by a product.");
    }

    categoryRepository.deleteById(id);
    return SuccessResponse.create("The category was deleted.");
  }


  private void validateCategoryNameInformed(CategoryRequest request) {
    if (isEmpty(request.getDescription())) {
      throw new ValidationException("The category description was not informed.");
    }
  }

  private void validateInformedId(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The category id was not informed.");
    }
  }


}
