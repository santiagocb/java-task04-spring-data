package com.ticketland.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    private String id;

    private double balance;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public UserAccount() {
    }

    public UserAccount(String id, double balance, User user) {
        this.id = id;
        this.balance = balance;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters and setters, constructors
}
