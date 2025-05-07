package com.github.konarjg.BackendAPI.dto;

import com.github.konarjg.BackendAPI.entity.Order;

import java.util.List;

public class UserDTO {
    private Long userId;
    private String email;
    private List<Order> orders;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
