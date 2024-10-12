package com.ticketland.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.ticketland.repos")
public class JpaConfig {
    // DataSource, EntityManagerFactory, TransactionManager, etc.
}