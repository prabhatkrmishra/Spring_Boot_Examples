package com.pkmprojects.socialmedia.loopin.controller;

import com.pkmprojects.socialmedia.loopin.repository.UserRepository;
import com.pkmprojects.socialmedia.loopin.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Register a new user.
     * POST localhost:8080/auth/register
     * Body: { "username": "john", "password": "secret", "role": "ROLE_USER" }
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("ROLE_USER");
        }
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully.");
    }

    /**
     * Login is handled automatically by Spring Security at /auth/login.
     * POST localhost:8080/auth/login  { username, password }
     */
    @GetMapping("/login")
    public ResponseEntity<String> loginPage() {
        return ResponseEntity.ok("Send POST to /auth/login with username & password.");
    }
}
