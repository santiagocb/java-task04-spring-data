package com.ticketland.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ticketland.entities.User;
import com.ticketland.entities.UserAccount;
import com.ticketland.repositories.UserAccountRepository;
import com.ticketland.services.UserAccountService;
import com.ticketland.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserAccountServiceTest {

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserAccountService userAccountService;

    private User user;
    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        user = new User("1", "John Doe", "john.doe@example.com");
        userAccount = new UserAccount("1", 0, user);
    }

    @Test
    void testCreateAccount() {
        when(userService.getById("1")).thenReturn(user);
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(userAccount);

        UserAccount createdAccount = userAccountService.createAccount("1");
        assertNotNull(createdAccount);
        assertEquals(user, createdAccount.getUser());
    }

    @Test
    void testRefillBalance() {
        when(userAccountRepository.findByUserId("1")).thenReturn(Optional.of(userAccount));
        userAccountService.refillBalance("1", 100.0);
        assertEquals(100.0, userAccount.getBalance());
        verify(userAccountRepository).save(userAccount);
    }

    @Test
    void testRefillBalanceAccountNotFound() {
        when(userAccountRepository.findByUserId("1")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userAccountService.refillBalance("1", 100.0));
    }

    @Test
    void testFindByUserId() {
        when(userAccountRepository.findByUserId("1")).thenReturn(Optional.of(userAccount));
        UserAccount foundAccount = userAccountService.findByUserId("1");
        assertNotNull(foundAccount);
        assertEquals("1", foundAccount.getId());
    }

    @Test
    void testFindByUserIdNotFound() {
        when(userAccountRepository.findByUserId("1")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userAccountService.findByUserId("1"));
    }

    @Test
    void testFindAll() {
        when(userAccountRepository.findAll()).thenReturn(Arrays.asList(userAccount));
        List<UserAccount> accounts = userAccountService.findAll();
        assertFalse(accounts.isEmpty());
        assertEquals(1, accounts.size());
    }
}