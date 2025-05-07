package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.dto.UserDTO;
import com.github.konarjg.BackendAPI.entity.Order;
import com.github.konarjg.BackendAPI.entity.OrderItem;
import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.requestBody.UserOrdersUpdateRequest;
import com.github.konarjg.BackendAPI.requestBody.UserRequest;
import com.github.konarjg.BackendAPI.requestBody.UserUpdateRequest;
import com.github.konarjg.BackendAPI.security.Hasher;
import com.github.konarjg.BackendAPI.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService, WarehouseService warehouseService, OrderService orderService, LocationService locationService, ProductService productService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<UserDTO> login(@RequestBody UserRequest credentials) {
        credentials.setPassword(Hasher.hash(credentials.getPassword()));

        User user = userService.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getUserId());
        userDTO.setOrders(user.getOrders());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/refresh-user")
    public ResponseEntity<UserDTO> refreshUser(@RequestBody UserRequest credentials) {
        if (!userService.existsByEmail(credentials.getEmail())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userService.findByEmail(credentials.getEmail());

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setUserId(user.getUserId());
        userDTO.setOrders(user.getOrders());

        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/register")
    public ResponseEntity<Void> register(@RequestBody UserRequest credentials) {
        credentials.setPassword(Hasher.hash(credentials.getPassword()));

        if (userService.existsByEmail(credentials.getEmail())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setEmail(credentials.getEmail());
        user.setPassword(Hasher.hash(credentials.getPassword()));
        user.setOrders(new ArrayList<>());

        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/update-credentials")
    public ResponseEntity<Void> updateCredentials(@RequestBody UserUpdateRequest credentials) {
        credentials.setNewPassword(Hasher.hash(credentials.getNewPassword()));

        User user = userService.findByEmailAndPassword(credentials.getOldEmail(), credentials.getOldPassword());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setEmail(credentials.getNewEmail());
        user.setPassword(credentials.getNewPassword());

        try {
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "/delete-account")
    public ResponseEntity<Void> deleteAccount(@RequestBody UserRequest credentials) {
        credentials.setPassword(Hasher.hash(credentials.getPassword()));

        User user = userService.findByEmailAndPassword(credentials.getEmail(), credentials.getPassword());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            userService.delete(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
