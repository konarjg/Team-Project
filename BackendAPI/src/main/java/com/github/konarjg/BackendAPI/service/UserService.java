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
        repository.save(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email, password);
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
