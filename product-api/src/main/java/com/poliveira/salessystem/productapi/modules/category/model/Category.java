package com.poliveira.salessystem.productapi.modules.category.model;

import com.poliveira.salessystem.productapi.modules.category.dto.CategoryRequest;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  public static Category of(CategoryRequest request) {
    var category = new Category();
    BeanUtils.copyProperties(request, category);
    return category;
  }
}
