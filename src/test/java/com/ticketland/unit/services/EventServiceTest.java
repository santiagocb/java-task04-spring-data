package com.ticketland.unit.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ticketland.entities.Event;
import com.ticketland.repositories.EventRepository;
import com.ticketland.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event("event1", "Concert", "Macarena", LocalDate.now(), 100.0);
    }

    @Test
    void testCreate() {
        when(eventRepository.save(event)).thenReturn(event);
        Event createdEvent = eventService.create(event);
        assertNotNull(createdEvent);
        assertEquals("Concert", createdEvent.getName());
    }

    @Test
    void testUpdateTicketPrice() {
        when(eventRepository.findById("event1")).thenReturn(Optional.of(event));
        eventService.updateTicketPrice("event1", 150.0);
        assertEquals(150.0, event.getTicketPrice());
        verify(eventRepository).save(event);
    }

    @Test
    void testUpdateTicketPriceNotFound() {
        when(eventRepository.findById("event1")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> eventService.updateTicketPrice("event1", 150.0));
    }

    @Test
    void testFindByEventId() {
        when(eventRepository.findById("event1")).thenReturn(Optional.of(event));
        Event foundEvent = eventService.findByEventId("event1");
        assertNotNull(foundEvent);
        assertEquals("event1", foundEvent.getId());
    }

    @Test
    void testFindByEventIdNotFound() {
        when(eventRepository.findById("event1")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> eventService.findByEventId("event1"));
    }

    @Test
    void testFindAll() {
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event));
        List<Event> events = eventService.findAll();
        assertFalse(events.isEmpty());
        assertEquals(1, events.size());
    }
}