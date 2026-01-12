package com.sumirvats2003.docier.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumirvats2003.docier.dto.SpaceRequest;
import com.sumirvats2003.docier.model.Space;
import com.sumirvats2003.docier.repository.SpaceRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SpaceService {
  @Autowired
  private SpaceRepository spaceRepository;

  public Optional<Space> getSpaceById(UUID id) {
    return spaceRepository.findById(id);
  }

  public Space createSpace(SpaceRequest spaceRequest) {
    Space space = new Space();
    space.fromDTO(spaceRequest);
    space.setCreationTimestamp(Instant.now().getEpochSecond());
    Space savedSpace = spaceRepository.save(space);

    return savedSpace;
  }

  public Space updateSpace(UUID id, SpaceRequest request) {
    Space existing = spaceRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No space found with id: " + id));

    existing.fromDTO(request);
    existing.setModificationTimestamp(Instant.now().getEpochSecond());
    return spaceRepository.save(existing);
  }

  public void deleteSpace(UUID id) {
    Space space = spaceRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No space found with id: " + id));

    spaceRepository.delete(space);
  }
}
