package com.sumirvats2003.docier.model;

import java.util.UUID;

import com.sumirvats2003.docier.dto.SpaceRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "spaces")
public class Space implements DataEntity<SpaceRequest> {
  @Id
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private UUID ownerId;

  private long creationTimestamp;

  private long modificationTimestamp;

  @Override
  public void fromDTO(SpaceRequest spaceRequest) {
    if (spaceRequest != null) {
      this.setId(UUID.randomUUID());
      this.setTitle(spaceRequest.getTitle());
      this.setOwnerId(spaceRequest.getOwnerId());
    }
  }
}
