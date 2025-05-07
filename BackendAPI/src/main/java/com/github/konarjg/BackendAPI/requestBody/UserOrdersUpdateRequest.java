package com.github.konarjg.BackendAPI.requestBody;

import java.util.List;

public class UserOrdersUpdateRequest {
    private String email;
    private List<OrderDTO> orders;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }
}
