package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.dto.ClientOrder;
import com.github.konarjg.BackendAPI.dto.ClientOrderItem;
import com.github.konarjg.BackendAPI.entity.Order;
import com.github.konarjg.BackendAPI.entity.OrderItem;
import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.requestBody.CreateOrderBody;
import com.github.konarjg.BackendAPI.service.OrderService;
import com.github.konarjg.BackendAPI.service.ProductService;
import com.github.konarjg.BackendAPI.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    public OrderController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientOrder>> getAll() {
        List<Order> orders = orderService.findAll();
        List<ClientOrder> clientOrders = orders.stream().map(o -> {
            ClientOrder order = new ClientOrder();
            order.setOrderId(o.getOrderId());
            order.setStatus(o.getStatus());
            order.setTotal(o.getTotal());
            order.setProducts(o.getProducts().stream().map(p -> {
                ClientOrderItem product = new ClientOrderItem();
                product.setProductId(p.getProduct().getProductId());
                product.setName(p.getProduct().getName());
                product.setQuantity(p.getQuantity());
                product.setImage(p.getProduct().getImage());
                product.setPrice(p.getProduct().getPrice());

                return product;
            }).collect(Collectors.toList()));

            return order;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(clientOrders, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateOrderBody data) {
        Order order = new Order();
        order.setUser(userService.findByEmail(data.getEmail()).orElse(null));
        order.setTotal(data.getTotal());
        order.setStatus(Order.Status.PREPARING);
        order.setProducts(data.getProducts().stream().map(p -> {
            OrderItem item = new OrderItem();
            item.setQuantity(p.getQuantity());
            Product product = productService.findById(p.getProductId());
            item.setProduct(product);
            productService.decreaseStock(product.getProductId(), item.getQuantity());

            return item;
        }).collect(Collectors.toList()));

        if (!orderService.save(order)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
