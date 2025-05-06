package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Orders o WHERE o.user.userId = :userId")
    public List<Order> findByUserId(@Param("userId") Long userId);
}
