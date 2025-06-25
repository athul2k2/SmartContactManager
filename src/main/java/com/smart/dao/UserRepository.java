package com.smart.dao;

import com.smart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Using method name query derivation
    Optional<User> findByEmail(String email);
}
