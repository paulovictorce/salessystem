package com.poliveira.salessystem.productapi;

import java.util.HashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {

  @GetMapping
  public ResponseEntity<HashMap<String, Object>> getApiStatus() {
    var response = new HashMap<String, Object>();

    response.put("service", "PRODUCT-API");
    response.put("status", "UP");
    response.put("httpStatus", HttpStatus.OK.value());

    return ResponseEntity.ok(response);
  }
}
