package com.sumirvats2003.docier.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "space_docs")
public class SpaceDocs {

  @EmbeddedId
  private SpaceDocId spaceDocId;
}
