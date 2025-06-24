package com.github.konarjg.BackendAPI.requestBody;

import java.util.List;

public class CreateCategoryBody {
    private String name;
    private String icon;
    private List<Long> products;

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

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }
}
