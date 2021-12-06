package com.poliveira.salessystem.productapi.modules.supplier.model;

import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierRequest;
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
@Table(name = "SUPPLIER")
public class Supplier {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "ID")
  private Integer id;

  @Column(name = "NAME", nullable = false)
  private String name;

  public static Supplier of(SupplierRequest request) {
    var supplier = new Supplier();
    BeanUtils.copyProperties(request, supplier);
    return supplier;
  }
}
