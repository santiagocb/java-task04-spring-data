package com.ticketland.unit.programs;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.User;
import com.ticketland.entities.UserAccount;
import com.ticketland.exceptions.InsufficientFundsException;
import com.ticketland.programs.BookingFacade;
import com.ticketland.services.EventService;
import com.ticketland.services.TicketService;
import com.ticketland.services.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingFacadeTest {

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private EventService eventService;

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private BookingFacade bookingFacade;

    private User user;
    private UserAccount userAccount;
    private Event event;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        user = new User("user1", "John Doe", "john.doe@example.com");
        userAccount = new UserAccount("user1", 100.0, user);
        event = new Event("event1", "Concert", "Macarena", LocalDate.now(), 100.0);
        ticket = new Ticket(userAccount, event);
    }

    @Test
    void testBookTicketSufficientFunds() {
        when(userAccountService.findByUserId("user1")).thenReturn(userAccount);
        when(eventService.findByEventId("event1")).thenReturn(event);

        bookingFacade.bookTicket("user1", "event1");

        verify(userAccountService).refillBalance("user1", -100.0);
    }

    @Test
    void testBookTicketInsufficientFunds() {
        event.setTicketPrice(200.0);
        when(userAccountService.findByUserId("user1")).thenReturn(userAccount);
        when(eventService.findByEventId("event1")).thenReturn(event);

        assertThrows(InsufficientFundsException.class, () -> bookingFacade.bookTicket("user1", "event1"));

        verify(ticketService, never()).generate(any(Ticket.class));
    }

    @Test
    void testCreateAccount() {
        bookingFacade.createAccount("user1");
        verify(userAccountService).createAccount("user1");
    }

    @Test
    void testRefillAccount() {
        bookingFacade.refillAccount("user1", 100.0);
        verify(userAccountService).refillBalance("user1", 100.0);
    }
}