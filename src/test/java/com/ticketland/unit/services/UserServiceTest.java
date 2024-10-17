package com.ticketland.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ticketland.entities.User;
import com.ticketland.repositories.UserRepository;
import com.ticketland.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("1", "John Doe", "john.doe@example.com");
    }

    @Test
    void testRegister() {
        userService.register(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testGetByIdFound() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        User foundUser = userService.getById("1");
        assertNotNull(foundUser);
        assertEquals("John Doe", foundUser.getName());
    }

    @Test
    void testGetByIdNotFound() {
        when(userRepository.findById("2")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getById("2"));
    }
}