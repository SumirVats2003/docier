package com.sumirvats2003.docier.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.sumirvats2003.docier.dto.DocumentRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "documents")
public class Document implements DataEntity<DocumentRequest> {
  @Id
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private UUID spaceId;

  private long creationTimestamp;

  private long modificationTimestamp;

  public void fromDTO(DocumentRequest documentRequest) {
    if (documentRequest != null) {
      this.setId(UUID.randomUUID());
      this.setTitle(documentRequest.getTitle());
      this.setContent(documentRequest.getContent());
      this.setSpaceId(documentRequest.getSpaceId());
    }
  }
}
