package com.github.konarjg.BackendAPI.dto;

import java.util.List;

public class ClientCategory {
    private long categoryId;
    private String name;
    private String icon;
    private List<ClientProduct> products;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<ClientProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ClientProduct> products) {
        this.products = products;
    }
}
