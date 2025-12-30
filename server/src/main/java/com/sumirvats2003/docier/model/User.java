package com.sumirvats2003.docier.model;

import java.util.UUID;

import com.sumirvats2003.docier.dto.AuthRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements DataEntity<AuthRequest> {
  @Id
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column()
  private long creationTimestamp;

  @Override
  public void fromDTO(AuthRequest authRequest) {
    if (authRequest != null) {
      this.setId(UUID.randomUUID());
      this.setName(authRequest.getName());
      this.setEmail(authRequest.getEmail());
      this.setPassword(authRequest.getPassword());
    }
  }
}
