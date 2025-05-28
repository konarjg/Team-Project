package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.entity.*;
import com.github.konarjg.BackendAPI.requestBody.OrderRequest;
import com.github.konarjg.BackendAPI.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private final Random random = new Random();
    private final OrderService orderService;
    private final ProductService productService;
    private final LocationService locationService;
    private final UserService userService;

    public OrderController(OrderService orderService, ProductService productService, LocationService locationService, UserService userService) {
        this.orderService = orderService;
        this.productService = productService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/fetch")
    public ResponseEntity<List<Order>> fetchOrders() {
        List<Order> orders = orderService.findAll();

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/fetch-with-emergency")
    public ResponseEntity<List<Order>> fetchOrdersWithEmergency() {
        List<Order> orders = orderService.findAllWithEmergency();

        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest request) {
        User user = userService.findByEmail(request.getUserEmail());
        Location destination = locationService.findById(request.getDestinationId());
        List<OrderItem> items = request.getItems().stream().map(i -> {
            OrderItem item = new OrderItem();
            item.setProduct(productService.findById(i.getProductId()));
            item.setQuantity(i.getQuantity());

            return item;
        }).toList();

        Order order = new Order();
        order.setUser(user);
        order.setDestination(destination);
        order.setItems(items);
        order.setState(request.getState());

        if (!orderService.save(order)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@RequestParam("orderId") long orderId) {
        Order order = orderService.findById(orderId);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!orderService.delete(order)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
