package com.ticketland.services;

import com.ticketland.repos.UserAccountRepository;
import com.ticketland.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void createAccount(String userId) {
        userAccountRepository.save(new UserAccount(userId, 0, userService.getById(userId)));
    }

    // Refills the balance for the given user's account
    @Transactional
    public void refillBalance(String userId, double amount) {
        UserAccount account = userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User account not found."));
        account.setBalance(account.getBalance() + amount);
        userAccountRepository.save(account);  // Save the updated balance
    }

    // Retrieves the UserAccount by userId
    @Transactional()
    public UserAccount findByUserId(String userId) {
        return userAccountRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User account not found."));
    }
}
