package com.github.konarjg.BackendAPI.requestBody;

import com.github.konarjg.BackendAPI.dto.OrderItemDTO;
import com.github.konarjg.BackendAPI.entity.OrderState;

import java.util.List;

public class OrderRequest {
    private String userEmail;
    private OrderState state;
    private long destinationId;
    private List<OrderItemDTO> items;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(long destinationId) {
        this.destinationId = destinationId;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }
}
