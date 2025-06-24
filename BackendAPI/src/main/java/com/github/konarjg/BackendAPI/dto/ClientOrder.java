package com.github.konarjg.BackendAPI.dto;

import com.github.konarjg.BackendAPI.entity.Order;

import java.util.List;

public class ClientOrder {
    private long orderId;
    private Order.Status status;
    private double total;
    private List<ClientOrderItem> products;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<ClientOrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<ClientOrderItem> products) {
        this.products = products;
    }
}
