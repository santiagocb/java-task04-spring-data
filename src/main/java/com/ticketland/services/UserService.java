package com.ticketland.services;

import com.ticketland.entities.User;
import com.ticketland.exceptions.UserNotFoundException;
import com.ticketland.programs.BookingFacade;
import com.ticketland.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    public static final Logger logger = LoggerFactory.getLogger(BookingFacade.class);

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void register(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    public List<User> findAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
