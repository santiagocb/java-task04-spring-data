package com.ticketland.services;

import com.ticketland.exceptions.UserNotFoundException;
import com.ticketland.repos.UserAccountRepository;
import com.ticketland.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void createAccount(String userId) {
        var user = userService.getById(userId);
        userAccountRepository.save(new UserAccount(userId, 0, user));
    }

    @Transactional
    public void refillBalance(String userId, double amount) {
        UserAccount account = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User account not found."));
        account.setBalance(account.getBalance() + amount);
        userAccountRepository.save(account);
    }

    @Transactional()
    public UserAccount findByUserId(String userId) {
        return userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User account not found."));
    }
}
