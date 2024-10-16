package com.ticketland.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
    @Id
    private String id;

    private String name;

    private String place;

    private LocalDate date;

    private double ticketPrice;

    public Event() {
    }

    public Event(String id, String name, String place, LocalDate date, double ticketPrice) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.date = date;
        this.ticketPrice = ticketPrice;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getPlace() {
        return place;
    }


    public LocalDate getDate() {
        return date;
    }


    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

