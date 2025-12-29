package com.sumirvats2003.docier.controller;

import com.sumirvats2003.docier.dto.LoginRequest;
import com.sumirvats2003.docier.dto.SignupRequest;
import com.sumirvats2003.docier.model.User;
import com.sumirvats2003.docier.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
    Optional<User> registeredUser = authService.signup(signupRequest);
    if (registeredUser.isPresent()) {
      return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    Optional<String> userJwt = authService.login(loginRequest);
    if (userJwt.isPresent()) {
      return new ResponseEntity<>(userJwt, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
    }
  }
}
