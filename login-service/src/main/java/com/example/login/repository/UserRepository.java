package com.example.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.login.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

