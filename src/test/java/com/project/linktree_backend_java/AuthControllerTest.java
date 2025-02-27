package com.project.linktree_backend_java;

import com.project.linktree_backend_java.controller.AuthController;
import com.project.linktree_backend_java.model.LoginRequest;
import com.project.linktree_backend_java.model.User;
import com.project.linktree_backend_java.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User("testUser", "test@example.com", "password123");

        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userService.registerUser(any(User.class))).thenReturn(user);

        ResponseEntity<String> response = authController.registerUser(user);
        assertEquals(201, response.getStatusCodeValue()); // ✅ Fixed status code
        assertEquals("User registered successfully!", response.getBody());
    }

    @Test
    void testRegisterUser_AlreadyExists() {
        User existingUser = new User("testUser", "test@example.com", "password123");

        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        ResponseEntity<String> response = authController.registerUser(existingUser);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("User already exists!", response.getBody());
    }

    @Test
    void testLoginUser_Success() {
        User user = new User("testUser", "test@example.com", "password123");

        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        LoginRequest request = new LoginRequest("test@example.com", "password123");

        ResponseEntity<String> response = authController.loginUser(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Login successful", response.getBody());
    }

    @Test
    void testLoginUser_InvalidPassword() {
        User user = new User("testUser", "test@example.com", "password123");

        when(userService.getUserByEmail("test@example.com")).thenReturn(Optional.of(user));

        LoginRequest request = new LoginRequest("test@example.com", "wrongpassword");

        ResponseEntity<String> response = authController.loginUser(request);
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
    }

    @Test
    void testLoginUser_UserNotFound() {
        LoginRequest request = new LoginRequest("notfound@example.com", "password123");

        when(userService.getUserByEmail("notfound@example.com")).thenReturn(Optional.empty());

        ResponseEntity<String> response = authController.loginUser(request);
        assertEquals(404, response.getStatusCodeValue());
        assertEquals("User not found", response.getBody());
    }
}
