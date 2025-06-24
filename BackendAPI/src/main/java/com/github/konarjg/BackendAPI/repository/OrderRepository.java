package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStatus(Order.Status status);
}
