package com.ticketland.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;

    private String name;

    private String email;

    public User() {
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


}
