package com.ticketland.services;

import com.ticketland.entities.Event;
import com.ticketland.exceptions.UserNotFoundException;
import com.ticketland.repos.EventRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventService {

    private EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Transactional
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public void updateTicketPrice(String eventId, double ticketPrice) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found."));
        event.setTicketPrice(ticketPrice);
        eventRepository.save(event);
    }

    @Transactional()
    public Event findByEventId(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new UserNotFoundException("Event not found."));
    }

    @Transactional
    public List<Event> findAll() {
        return StreamSupport.stream(eventRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
}
