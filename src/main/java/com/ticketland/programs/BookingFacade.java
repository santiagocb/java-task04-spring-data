package com.ticketland.programs;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.UserAccount;
import com.ticketland.exceptions.InsufficientFundsException;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BookingFacade {

    public static final Logger logger = LoggerFactory.getLogger(BookingFacade.class);


    private final UserAccountService userAccountService;
    private final EventService eventService;
    private final TicketService ticketService;

    public BookingFacade(UserAccountService userAccountService, EventService eventService, TicketService ticketService) {
        this.userAccountService = userAccountService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Transactional
    public void bookTicket(String userId, String eventId) throws InsufficientFundsException {
        UserAccount account = userAccountService.findByUserId(userId);
        Event event = eventService.findByEventId(eventId);

        if (account.getBalance() < event.getTicketPrice()) {
            throw new InsufficientFundsException("Insufficient funds to book the ticket.");
        }
        account.setBalance(account.getBalance() - event.getTicketPrice());
        userAccountService.refillBalance(userId, event.getTicketPrice() * -1);  // Save the updated balance

        ticketService.generate(new Ticket(account, event));
    }

    @Transactional
    public void refillAccount(String userId, double amount) {
        userAccountService.refillBalance(userId, amount);
    }
}
