package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
}
