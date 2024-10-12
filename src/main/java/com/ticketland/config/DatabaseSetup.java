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
                "id BIGSERIAL PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "surname VARCHAR(100), " +
                "birthdate VARCHAR(100))");

        jdbcTemplate.execute("CREATE TABLE user_accounts (" +
                "id VARCHAR(255) PRIMARY KEY," +
                "balance NUMERIC(10, 2) NOT NULL," +
                "user_id VARCHAR(255) NOT NULL," +
                "FOREIGN KEY (user_id) REFERENCES users(id))");
    }

    public void dropTables() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS Likes");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Posts");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Friendships");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Users");
    }
}
