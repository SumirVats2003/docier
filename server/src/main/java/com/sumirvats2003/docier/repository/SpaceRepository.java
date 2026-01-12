package com.sumirvats2003.docier.repository;

import com.sumirvats2003.docier.model.Space;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpaceRepository extends JpaRepository<Space, UUID> { }
