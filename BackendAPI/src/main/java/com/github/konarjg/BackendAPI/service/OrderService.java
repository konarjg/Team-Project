package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Order;
import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.repository.CategoryRepository;
import com.github.konarjg.BackendAPI.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAllByStatus(Order.Status status) {
        return orderRepository.findByStatus(status);
    }

    @Transactional
    public void updateStatus(Long orderId, Order.Status newStatus) {
        Order order = findById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            orderRepository.save(order);
        } else {
            System.err.println("Could not find order with ID: " + orderId + " to update status.");
        }
    }

    @Transactional
    public boolean save(Order order) {
        try {
            orderRepository.save(order);
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
