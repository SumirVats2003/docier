package com.sumirvats2003.docier.dto;

import lombok.Data;

@Data
public class LoginRequest {
  private String email;
  private String password;
}
