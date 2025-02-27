package com.project.linktree_backend_java;

import com.project.linktree_backend_java.model.User;
import com.project.linktree_backend_java.repository.UserRepository;
import com.project.linktree_backend_java.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User("testUser", "test@example.com", "password123");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.registerUser(user);
        assertNotNull(savedUser);
        assertEquals("testUser", savedUser.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testRegisterUser_DuplicateEmail() {
        User existingUser = new User("testUser", "test@example.com", "password123");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(existingUser));

        Exception exception = assertThrows(RuntimeException.class, () -> userService.registerUser(existingUser));
        assertEquals("User already exists!", exception.getMessage());
    }

    @Test
    void testGetUserByEmail_Success() {
        User user = new User("testUser", "test@example.com", "password123");

        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.getUserByEmail("test@example.com");
        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
    }

    @Test
    void testGetUserByEmail_NotFound() {
        when(userRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.getUserByEmail("notfound@example.com");
        assertFalse(foundUser.isPresent());
    }
}
