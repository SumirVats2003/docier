package com.sumirvats2003.docier.service;

import com.sumirvats2003.docier.dto.AuthRequest;
import com.sumirvats2003.docier.model.User;
import com.sumirvats2003.docier.repository.AuthRepository;
import com.sumirvats2003.docier.utils.JwtUtils;
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
  public Optional<User> signup(AuthRequest authRequest) {
    try {
      if (authRepository.findByEmail(authRequest.getEmail()).isPresent()) {
        return Optional.empty();
      }
      User user = new User();
      user.setName(authRequest.getName());
      user.setEmail(authRequest.getEmail());
      user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
      user.setCreationTimestamp(Instant.now().getEpochSecond());

      authRepository.save(user);
      return Optional.of(user);
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException(e.toString());
    }
  }

  @Transactional
  public Optional<String> login(AuthRequest authRequest) {
    try {
      Optional<User> userOptional = authRepository.findByEmail(authRequest.getEmail());
      if (userOptional.isPresent()) {
        User user = userOptional.get();
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
          return Optional.of(jwtUtils.generateToken(authRequest.getEmail()));
        }
      }
      return Optional.empty();
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException(e.toString());
    }
  }
}
