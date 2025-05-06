package com.github.konarjg.BackendAPI.entity;

import jakarta.persistence.*;

@Entity(name = "WarehouseItems")
public class WarehouseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseItemId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Product product;
    private int quantity;

    public long getWarehouseItemId() {
        return warehouseItemId;
    }

    public void setWarehouseItemId(long warehouseItemId) {
        this.warehouseItemId = warehouseItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}