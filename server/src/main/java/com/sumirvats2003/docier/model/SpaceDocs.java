package com.sumirvats2003.docier.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "space_docs")
public class SpaceDocs {
  @Column(nullable = false)
  private UUID spaceId;

  @Column(nullable = false)
  private UUID documentId;
}
