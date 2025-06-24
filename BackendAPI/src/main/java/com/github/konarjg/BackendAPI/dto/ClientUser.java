package com.github.konarjg.BackendAPI.dto;

import com.github.konarjg.BackendAPI.entity.Order;

import java.util.List;

public class ClientUser {
    private String name;
    private String email;
    private List<ClientOrder> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ClientOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ClientOrder> orders) {
        this.orders = orders;
    }
}
