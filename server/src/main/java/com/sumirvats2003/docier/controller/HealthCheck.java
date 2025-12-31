package com.sumirvats2003.docier.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthCheck {

  @GetMapping("/healthcheck")
  public ResponseEntity<?> healthCheck() {
    return new ResponseEntity<>("Docier : Server Connected Successfully", HttpStatus.OK);
  }

}
