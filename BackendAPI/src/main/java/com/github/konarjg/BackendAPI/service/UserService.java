package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.repository.UserRepository;
import com.github.konarjg.BackendAPI.security.Hasher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
