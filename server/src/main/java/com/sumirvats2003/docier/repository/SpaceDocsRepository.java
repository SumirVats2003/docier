package com.sumirvats2003.docier.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumirvats2003.docier.model.SpaceDocs;

public interface SpaceDocsRepository extends JpaRepository<SpaceDocs, UUID> {
  void deleteByDocumentId(UUID id);
}
