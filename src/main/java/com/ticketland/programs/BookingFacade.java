package com.ticketland.programs;

import com.ticketland.entities.UserAccount;
import com.ticketland.exceptions.InsufficientFundsException;
import com.ticketland.services.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookingFacade {

    public static final Logger logger = LoggerFactory.getLogger(BookingFacade.class);


    private final UserAccountService userAccountService;

    public BookingFacade(UserAccountService userAccountService) {

        this.userAccountService = userAccountService;

        logger.debug("Booking service created");
    }

    /*@Transactional
    public void bookTicket(String userId, Event event) throws InsufficientFundsException {
        UserAccount account = userAccountService.findByUserId(userId);
        if (account.getBalance() < event.getTicketPrice()) {
            throw new InsufficientFundsException("Insufficient funds to book the ticket.");
        }
        account.setBalance(account.getBalance() - event.getTicketPrice());
        userAccountService.refillBalance(userId, event.getTicketPrice() * -1);  // Save the updated balance
        // Add additional logic for creating and saving the Ticket object, etc.
    }*/


    @Transactional
    public void refillAccount(String userId, double amount) {
        userAccountService.refillBalance(userId, amount);
    }
}
