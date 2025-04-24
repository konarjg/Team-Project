package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> getUser; HttpEntity<?> ByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser (@PathVariable String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            userService.delete(user);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}