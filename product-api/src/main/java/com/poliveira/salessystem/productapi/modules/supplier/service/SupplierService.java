package com.poliveira.salessystem.productapi.modules.supplier.service;


import static org.springframework.util.ObjectUtils.isEmpty;

import com.poliveira.salessystem.productapi.config.exception.ValidationException;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierRequest;
import com.poliveira.salessystem.productapi.modules.supplier.dto.SupplierResponse;
import com.poliveira.salessystem.productapi.modules.supplier.model.Supplier;
import com.poliveira.salessystem.productapi.modules.supplier.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  @Autowired
  private SupplierRepository supplierRepository;

  public Supplier findById(Integer id) {
    return supplierRepository.findById(id).orElseThrow(() -> new ValidationException("There's no supplier for the given ID."));
  }

  public SupplierResponse save(SupplierRequest request) {
    validateSupplierNameInformed(request);
    var supplier = supplierRepository.save(Supplier.of(request));
    return SupplierResponse.of((supplier));
  }

  private void validateSupplierNameInformed(SupplierRequest request) {
    if (isEmpty(request.getName())) {
      throw new ValidationException("The supplier name was not informed.");
    }
  }


}
