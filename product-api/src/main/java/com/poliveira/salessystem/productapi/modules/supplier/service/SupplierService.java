package com.poliveira.salessystem.productapi.modules.supplier.service;


import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.ValidationException;
import com.poliveira.salessystem.productapi.config.response.SuccessResponse;
import com.poliveira.salessystem.productapi.modules.product.service.ProductService;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierRequest;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierResponse;
import com.poliveira.salessystem.productapi.modules.supplier.model.Supplier;
import com.poliveira.salessystem.productapi.modules.supplier.repository.SupplierRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  @Autowired
  private SupplierRepository supplierRepository;

  @Autowired
  private ProductService productService;

  public Supplier findById(Integer id) {
    validateInformedId(id);
    return supplierRepository.findById(id)
        .orElseThrow(() -> new ValidationException("There's no supplier for the given ID."));
  }

  public SupplierResponse findByIdResponse(Integer id) {
    return SupplierResponse.of(findById(id));
  }

  public List<SupplierResponse> findAll() {
    return supplierRepository.findAll().stream().map(SupplierResponse::of)
        .collect(
            Collectors.toList());
  }

  public List<SupplierResponse> findByName(String name) {
    if (isEmpty(name)) {
      throw new ValidationException("The supllier name must be informed.");
    }
    return supplierRepository.findByNameIgnoreCaseContaining(name).stream()
        .map(SupplierResponse::of)
        .collect(
            Collectors.toList());
  }

  public SupplierResponse save(SupplierRequest request) {
    validateSupplierNameInformed(request);
    var supplier = supplierRepository.save(Supplier.of(request));
    return SupplierResponse.of((supplier));
  }

  public SupplierResponse update(SupplierRequest request, Integer id) {
    validateSupplierNameInformed(request);
    validateInformedId(id);
    var supplier = Supplier.of(request);
    supplier.setId(id);
    supplierRepository.save(supplier);
    return SupplierResponse.of((supplier));
  }

  public SuccessResponse delete(Integer id) {
    validateInformedId(id);
    if (productService.existsBySupplierId(id)) {
      throw new ValidationException(
          "You cannot delete this supplier because it's already defined by a product.");
    }

    supplierRepository.deleteById(id);
    return SuccessResponse.create("The supplier was deleted.");
  }

  private void validateSupplierNameInformed(SupplierRequest request) {
    if (isEmpty(request.getName())) {
      throw new ValidationException("The supplier name was not informed.");
    }
  }

  private void validateInformedId(Integer id) {
    if (isEmpty(id)) {
      throw new ValidationException("The supplier id was not informed.");
    }
  }


}
