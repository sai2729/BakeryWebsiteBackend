package com.bakery.BakeryManagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bakery.BakeryManagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}