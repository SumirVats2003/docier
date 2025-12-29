package com.sumirvats2003.docier.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumirvats2003.docier.dto.DocumentRequest;
import com.sumirvats2003.docier.model.Document;
import com.sumirvats2003.docier.model.SpaceDocs;
import com.sumirvats2003.docier.repository.DocumentRepository;
import com.sumirvats2003.docier.repository.SpaceDocsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DocumentService {
  @Autowired
  private DocumentRepository documentRepository;

  @Autowired
  private SpaceDocsRepository spaceDocsRepository;

  public Optional<Document> getDocumentById(UUID id) {
    return documentRepository.findById(id);
  }

  @Transactional
  public Document createDocument(DocumentRequest documentRequest) {
    Document document = new Document();
    document.fromDTO(documentRequest);
    document.setCreationTimestamp(Instant.now().getEpochSecond());
    Document savedDocument = documentRepository.save(document);

    SpaceDocs spaceDocs = new SpaceDocs();
    spaceDocs.setSpaceId(savedDocument.getSpaceId());
    spaceDocs.setDocumentId(savedDocument.getId());
    spaceDocsRepository.save(spaceDocs);

    return savedDocument;
  }

  public Document updateDocument(UUID id, DocumentRequest request) {
    Document existing = documentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No document found with id: " + id));

    existing.fromDTO(request);
    existing.setModificationTimestamp(Instant.now().getEpochSecond());
    return documentRepository.save(existing);
  }

  @Transactional
  public void deleteDocument(UUID id) {
    Document document = documentRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("No document found with id: " + id));

    spaceDocsRepository.deleteByDocumentId(id);
    documentRepository.delete(document);
  }
}
