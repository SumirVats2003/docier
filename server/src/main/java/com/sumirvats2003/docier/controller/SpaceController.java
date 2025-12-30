package com.sumirvats2003.docier.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sumirvats2003.docier.dto.SpaceRequest;
import com.sumirvats2003.docier.model.Space;
import com.sumirvats2003.docier.service.SpaceService;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

  @Autowired
  private SpaceService spaceService;

  @GetMapping("/{id}")
  public ResponseEntity<?> getSpaceById(@PathVariable UUID id) {
    try {
      Optional<Space> space = spaceService.getSpaceById(id);
      if (space.isPresent()) {
        return new ResponseEntity<>(space, HttpStatus.OK);
      } else {
        return new ResponseEntity<>("No space with id: " + id, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<?> createSpace(@RequestBody SpaceRequest request) {
    try {
      Space created = spaceService.createSpace(request);
      return new ResponseEntity<>(created, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> updateSpace(@PathVariable UUID id, @RequestBody SpaceRequest request) {
    try {
      Space updated = spaceService.updateSpace(id, request);
      return new ResponseEntity<>(updated, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSpace(@PathVariable UUID id) {
    try {
      spaceService.deleteSpace(id);
      return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
      return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
