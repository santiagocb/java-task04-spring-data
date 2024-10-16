package com.ticketland.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseSetup {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSetup(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void createTables() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users (" +
                "id VARCHAR(255) PRIMARY KEY, " +
                "name VARCHAR(255), " +
                "email VARCHAR(255))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS user_accounts (" +
                "id VARCHAR(255) PRIMARY KEY," +
                "balance NUMERIC(10, 2) NOT NULL," +
                "userId VARCHAR(255) NOT NULL," +
                "FOREIGN KEY (userId) REFERENCES users(id))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS events (" +
                "id VARCHAR(255) PRIMARY KEY," +
                "name VARCHAR(255)," +
                "place VARCHAR(255)," +
                "date DATE," +
                "ticketPrice NUMERIC(10, 2))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS tickets (" +
                "id VARCHAR(255) PRIMARY KEY," +
                "userAccountId VARCHAR(255)," +
                "eventId VARCHAR(255)," +
                "FOREIGN KEY (eventId) REFERENCES events(id)," +
                "FOREIGN KEY (userAccountId) REFERENCES user_accounts(id))");
    }

    public void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS tickets");
        jdbcTemplate.execute("DROP TABLE IF EXISTS user_accounts");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("DROP TABLE IF EXISTS events");
    }
}
