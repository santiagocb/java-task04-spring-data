package com.ticketland.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ticketland.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
