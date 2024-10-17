package com.ticketland.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.User;
import com.ticketland.entities.UserAccount;
import com.ticketland.repositories.TicketRepository;
import com.ticketland.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    private User user;
    private UserAccount userAccount;
    private Event event;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        user = new User("1", "John Doe", "john.doe@example.com");
        userAccount = new UserAccount("1", 0, user);
        event = new Event("event1", "Concert", "Macarena", LocalDate.now(), 100.0);
        ticket = new Ticket(userAccount, event);
    }

    @Test
    void testGenerate() {
        ticketService.generate(ticket);
        verify(ticketRepository).save(ticket);
    }

    @Test
    void testFindTicketsByAccountUserId() {
        when(ticketRepository.findTicketsByUserAccountId("1")).thenReturn(Arrays.asList(ticket));
        List<Ticket> tickets = ticketService.findTicketsByAccountUserId("1");
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
    }

    @Test
    void testFindAll() {
        when(ticketRepository.findAll()).thenReturn(Arrays.asList(ticket));
        List<Ticket> tickets = ticketService.findAll();
        assertFalse(tickets.isEmpty());
        assertEquals(1, tickets.size());
    }
}