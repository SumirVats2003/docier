package com.sumirvats2003.docier.service;

import com.sumirvats2003.docier.model.User;
import com.sumirvats2003.docier.repository.AuthRepository;
import com.sumirvats2003.docier.utils.JwtUtils;
import com.sumirvats2003.docier.dto.LoginRequest;
import com.sumirvats2003.docier.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthService {

  @Autowired
  private AuthRepository authRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtils jwtUtils;

  @Transactional
  public Optional<User> signup(SignupRequest signupRequest) {
    try {
      if (authRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
        return Optional.empty();
      }
      User user = new User();
      user.setEmail(signupRequest.getEmail());
      user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
      user.setCreationTimestamp(Instant.now().getEpochSecond());

      authRepository.save(user);
      return Optional.of(user);
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException(e.toString());
    }
  }

  @Transactional
  public Optional<String> login(LoginRequest loginRequest) {
    try {
      Optional<User> userOptional = authRepository.findByEmail(loginRequest.getEmail());
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
          return Optional.of(jwtUtils.generateToken(loginRequest.getEmail()));
        }
      }
      return Optional.empty();
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException(e.toString());
    }
  }
}
