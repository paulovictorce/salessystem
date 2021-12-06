package com.poliveira.salessystem.productapi.modules.supplier.controller;

import com.poliveira.salessystem.productapi.config.response.SuccessResponse;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierRequest;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierResponse;
import com.poliveira.salessystem.productapi.modules.supplier.service.SupplierService;
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
@RequestMapping("/product-api/supplier")
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  @GetMapping
  public List<SupplierResponse> findAll() {
    return supplierService.findAll();
  }

  @GetMapping("{id}")
  public SupplierResponse findById(@PathVariable Integer id) {
    return supplierService.findByIdResponse(id);
  }

  @GetMapping("name/{name}")
  public List<SupplierResponse> findByDescription(@PathVariable String name) {
    return supplierService.findByName(name);
  }

  @PostMapping
  public SupplierResponse save(@RequestBody SupplierRequest request) {
    return supplierService.save(request);
  }

  @PutMapping("{id}")
  public SupplierResponse update(@RequestBody SupplierRequest request, @PathVariable Integer id) {
    return supplierService.update(request, id);
  }

  @DeleteMapping("{id}")
  public SuccessResponse delete(@PathVariable Integer id) {
    return supplierService.delete(id);
  }
}
