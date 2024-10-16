package com.ticketland.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSetup {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSetup(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

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
    }

    public void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS user_accounts");
        jdbcTemplate.execute("DROP TABLE IF EXISTS users");
        jdbcTemplate.execute("DROP TABLE IF EXISTS events");
    }
}
