package com.ticketland.integration.repositories;

import com.ticketland.entities.Event;
import com.ticketland.entities.Ticket;
import com.ticketland.entities.User;
import com.ticketland.entities.UserAccount;
import com.ticketland.integration.RepositoryTestConfiguration;
import com.ticketland.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RepositoryTestConfiguration.class)
@Transactional
public class TicketRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testFindTicketsByUserAccountId() {
        User user = new User("userId", "John Doe", "");
        UserAccount userAccount = new UserAccount("userAccountId", 100.0, user);
        Event event = new Event("eventId", "Concert", "City Hall", LocalDate.now(), 50.0);
        Ticket ticket = new Ticket(userAccount, event);

        entityManager.persist(user);
        entityManager.persist(userAccount);
        entityManager.persist(event);
        entityManager.persist(ticket);
        entityManager.flush();

        List<Ticket> tickets = ticketRepository.findTicketsByUserAccountId("userAccountId");
        assertEquals(1, tickets.size());
        assertEquals("userAccountId", tickets.get(0).getUser().getId());
    }
}