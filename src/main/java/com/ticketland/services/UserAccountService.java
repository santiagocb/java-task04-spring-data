package com.ticketland.services;

import com.ticketland.entities.UserAccount;
import com.ticketland.exceptions.UserNotFoundException;
import com.ticketland.repos.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserAccountService {

    private UserAccountRepository userAccountRepository;

    private UserService userService;

    public UserAccountService(UserAccountRepository userAccountRepository, UserService userService) {
        this.userAccountRepository = userAccountRepository;
        this.userService = userService;
    }

    public UserAccount createAccount(String userId) {
        var user = userService.getById(userId);
        return userAccountRepository.save(new UserAccount(userId, 0, user));
    }

    public void refillBalance(String userId, double amount) {
        UserAccount account = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User account not found."));
        System.out.println("Added to the balance: " + amount);
        account.setBalance(account.getBalance() + amount);
        userAccountRepository.save(account);
    }

    public UserAccount findByUserId(String userId) {
        return userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User account not found."));
    }

    public List<UserAccount> findAll() {
        return StreamSupport.stream(userAccountRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
