package com.github.konarjg.BackendAPI.entity;

import jakarta.persistence.*;

@Entity(name = "WarehouseItems")
public class WarehouseItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long warehouseItemId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;
    private long quantity;

    public WarehouseItem() {

    }

    public WarehouseItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}