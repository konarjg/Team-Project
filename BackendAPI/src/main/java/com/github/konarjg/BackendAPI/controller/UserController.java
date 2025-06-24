package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.dto.ClientOrder;
import com.github.konarjg.BackendAPI.dto.ClientOrderItem;
import com.github.konarjg.BackendAPI.dto.ClientUser;
import com.github.konarjg.BackendAPI.entity.User;
import com.github.konarjg.BackendAPI.requestBody.LoginBody;
import com.github.konarjg.BackendAPI.requestBody.RegisterBody;
import com.github.konarjg.BackendAPI.requestBody.UpdateUserBody;
import com.github.konarjg.BackendAPI.security.Hasher;
import com.github.konarjg.BackendAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/details/{email}")
    public ResponseEntity<ClientUser> getUserDetails(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(user -> ResponseEntity.ok(mapUserToClientUser(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<ClientUser> login(@RequestBody LoginBody data) {
        return userService.findByEmail(data.getEmail())
                .map(user -> {
                    String hashedPassword = Hasher.hash(data.getPassword());
                    if (user.getPassword().equals(hashedPassword)) {
                        return new ResponseEntity<>(mapUserToClientUser(user), HttpStatus.OK);
                    } else {
                        return new ResponseEntity<ClientUser>(HttpStatus.UNAUTHORIZED);
                    }
                })
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateUserBody data) {
        return userService.findByEmail(data.getEmail()).map(existingUser -> {
            existingUser.setName(data.getName());

            if (data.getPassword() != null && !data.getPassword().isEmpty()) {
                existingUser.setPassword(Hasher.hash(data.getPassword()));
            }

            userService.save(existingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterBody data) {
        if (userService.findByEmail(data.getEmail()).isPresent()) {
            return new ResponseEntity<>("Email is already in use.", HttpStatus.CONFLICT);
        }

        User user = new User();
        user.setEmail(data.getEmail());
        user.setPassword(Hasher.hash(data.getPassword()));
        user.setName(data.getName());
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private ClientUser mapUserToClientUser(User user) {
        ClientUser clientUser = new ClientUser();
        clientUser.setEmail(user.getEmail());
        clientUser.setName(user.getName());
        clientUser.setOrders(user.getOrders().stream().map(order -> {
            ClientOrder clientOrder = new ClientOrder();
            clientOrder.setOrderId(order.getOrderId());
            clientOrder.setStatus(order.getStatus());
            clientOrder.setTotal(order.getTotal());
            clientOrder.setProducts(order.getProducts().stream().map(item -> {
                ClientOrderItem clientItem = new ClientOrderItem();
                clientItem.setProductId(item.getProduct().getProductId());
                clientItem.setName(item.getProduct().getName());
                clientItem.setQuantity(item.getQuantity());
                clientItem.setImage(item.getProduct().getImage());
                clientItem.setPrice(item.getProduct().getPrice());
                return clientItem;
            }).collect(Collectors.toList()));
            return clientOrder;
        }).collect(Collectors.toList()));
        return clientUser;
    }
}