package com.poliveira.salessystem.productapi.modules.category.service;


import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.ValidationException;
import com.poliveira.salessystem.productapi.modules.category.dto.CategoryRequest;
import com.poliveira.salessystem.productapi.modules.category.dto.CategoryResponse;
import com.poliveira.salessystem.productapi.modules.category.model.Category;
import com.poliveira.salessystem.productapi.modules.category.repository.CategoryRepository;
import com.poliveira.salessystem.productapi.modules.supplier.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

  public Category findById(Integer id) {
    return categoryRepository.findById(id).orElseThrow(() -> new ValidationException("There's no category for the given ID."));
  }

  public CategoryResponse save(CategoryRequest request) {
    validateCategoryNameInformed(request);
    var category = categoryRepository.save(Category.of(request));
    return CategoryResponse.of((category));
  }

  private void validateCategoryNameInformed(CategoryRequest request) {
    if (isEmpty(request.getDescription())) {
      throw new ValidationException("The category description was not informed.");
    }
  }


}
