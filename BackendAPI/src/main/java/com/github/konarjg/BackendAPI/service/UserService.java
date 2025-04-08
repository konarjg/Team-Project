package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void save(User user) {

    }

    public void delete(User user) {

    }

    public User findByEmail(String email) {
        return null;
    }

    public User findByEmailAndPassword(String email, String password) {
        return null;
    }

    public boolean existsByEmail(String email) {
        return false;
    }

}
