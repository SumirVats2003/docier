package com.sumirvats2003.docier.repository;

import com.sumirvats2003.docier.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
