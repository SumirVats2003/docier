package com.sumirvats2003.docier.repository;

import com.sumirvats2003.docier.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> { }
