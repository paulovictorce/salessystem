package com.poliveira.salessystem.productapi.modules.category.controller;

import com.poliveira.salessystem.productapi.modules.category.dto.CategoryRequest;
import com.poliveira.salessystem.productapi.modules.category.dto.CategoryResponse;
import com.poliveira.salessystem.productapi.modules.category.service.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public List<CategoryResponse> findAll() {
    return categoryService.findAll();
  }

  @GetMapping("{id}")
  public CategoryResponse findById(@PathVariable Integer id) {
    return categoryService.findByIdResponse(id);
  }

  @GetMapping("description/{description}")
  public List<CategoryResponse> findByDescription(@PathVariable String description) {
    return categoryService.findByDescription(description);
  }

  @PostMapping
  public CategoryResponse save(@RequestBody CategoryRequest request) {
    return categoryService.save(request);
  }
}
