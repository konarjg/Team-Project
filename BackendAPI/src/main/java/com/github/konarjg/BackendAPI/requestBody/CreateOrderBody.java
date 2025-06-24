package com.github.konarjg.BackendAPI.requestBody;

import com.github.konarjg.BackendAPI.dto.ClientOrderItem;

import java.util.List;

public class CreateOrderBody {
    private String email;
    private double total;
    private List<ClientOrderItem> products;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
