package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Order;
import com.github.konarjg.BackendAPI.entity.OrderState;
import com.github.konarjg.BackendAPI.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order findRandom() {
        return orderRepository.findRandom();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public boolean save(Order order) {
        try {
            orderRepository.save(order);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Order order) {
        try {
            orderRepository.delete(order);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> findAllWithEmergency() {
        return orderRepository.findAllWithEmergency(OrderState.DELAYED, OrderState.LOST);
    }

}
