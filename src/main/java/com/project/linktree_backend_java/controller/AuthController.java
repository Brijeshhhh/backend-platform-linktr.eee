package com.project.linktree_backend_java.controller;

import com.project.linktree_backend_java.model.LoginRequest;
import com.project.linktree_backend_java.model.User;
import com.project.linktree_backend_java.repository.UserRepository;
import com.project.linktree_backend_java.service.UserService;
import jakarta.servlet.http.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        User user = userService.registerUser(request.get("username"), request.get("email"), request.get("password"), request.get("referralCode"));
        return ResponseEntity.ok(Map.of("message", "User registered successfully", "userId", user.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        boolean isValid = userService.verifyPassword(request.get("password"), request.get("encodedPassword"));
        if (!isValid) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of("message", "Login successful"));
    }

    @GetMapping("/referrals")
    public ResponseEntity<?> getReferrals(@RequestParam String username) {
        return ResponseEntity.ok(userService.getUserReferrals(username));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletResponse response) {
        // Clear JWT cookie
        Cookie cookie = new Cookie("JWT_TOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("Logged out successfully");
    }

}
