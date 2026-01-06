package com.sumirvats2003.docier.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class SpaceDocId implements Serializable {
  private UUID spaceId;
  private UUID documentId;
}
