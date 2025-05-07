package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Order;
import com.github.konarjg.BackendAPI.entity.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Orders o ORDER BY FUNCTION('RAND')")
    public Order findRandom();
    @Query("SELECT o FROM Orders o WHERE o.state IN(:delayed, :lost)")
    public List<Order> findAllWithEmergency(@Param("delayed") OrderState delayed, @Param("lost") OrderState lost);
}
