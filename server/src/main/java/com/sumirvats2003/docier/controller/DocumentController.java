package com.sumirvats2003.docier.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumirvats2003.docier.dto.DocumentRequest;
import com.sumirvats2003.docier.model.Document;
import com.sumirvats2003.docier.service.DocumentService;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

  private final DocumentService documentService;

  public DocumentController(DocumentService documentService) {
    this.documentService = documentService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getDocumentById(@PathVariable UUID id) {
    try {
      Optional<Document> document = documentService.getDocumentById(id);
      if (document.isPresent()) {
        return new ResponseEntity<>(document, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No document with id: " + id, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<?> createDocument(@RequestBody DocumentRequest request) {
    try {
      Document created = documentService.createDocument(request);
      return new ResponseEntity<>(created, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateDocument(@PathVariable UUID id, @RequestBody DocumentRequest request) {
    try {
      Document updated = documentService.updateDocument(id, request);
      return new ResponseEntity<>(updated, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteDocument(@PathVariable UUID id) {
    try {
      documentService.deleteDocument(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
