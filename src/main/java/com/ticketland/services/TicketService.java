package com.ticketland.services;

import com.ticketland.entities.Ticket;
import com.ticketland.exceptions.UserNotFoundException;
import com.ticketland.repos.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public void generate(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket findTicketById(String ticketId) {
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new UserNotFoundException("Ticket not found."));
    }

    public List<Ticket> findTicketsByAccountUserId(String userAccountId) {
        return ticketRepository.findTicketsByUserAccountId(userAccountId);
    }

    public List<Ticket> findAll() {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
