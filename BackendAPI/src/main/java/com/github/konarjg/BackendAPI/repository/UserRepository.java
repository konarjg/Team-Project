package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByEmail(String email);
    public User findByEmailAndPassword(String email, String password);
    public boolean existsByEmail(String email);
}
