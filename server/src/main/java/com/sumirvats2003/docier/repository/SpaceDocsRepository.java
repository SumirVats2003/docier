package com.sumirvats2003.docier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumirvats2003.docier.model.SpaceDocId;
import com.sumirvats2003.docier.model.SpaceDocs;

public interface SpaceDocsRepository extends JpaRepository<SpaceDocs, SpaceDocId> {
  void deleteBySpaceDocId(SpaceDocId spaceDocId);
  List<SpaceDocs> findBySpaceDocId(SpaceDocId spaceDocId);
}
