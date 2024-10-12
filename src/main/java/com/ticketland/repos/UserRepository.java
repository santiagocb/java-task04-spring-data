package com.ticketland.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ticketland.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}