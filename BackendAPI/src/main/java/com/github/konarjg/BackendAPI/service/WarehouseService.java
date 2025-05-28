package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.entity.Warehouse;
import com.github.konarjg.BackendAPI.entity.WarehouseItem;
import com.github.konarjg.BackendAPI.repository.WarehouseRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse findById(long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public List<WarehouseItem> getFullStockList() {
        return warehouseRepository.getFullStockList();
    }

    public WarehouseItem getStock(String productName) {
        return warehouseRepository.getStock(productName);
    }

    public void addProduct(long warehouseId, Product product, long quantity) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);

        WarehouseItem item = warehouse.getItems().stream().filter(x -> x.getProduct().getProductId()
                        == product.getProductId())
                .findFirst().orElse(null);

        if (item == null) {
            warehouse.getItems().add(new WarehouseItem(product, quantity));
        }
        else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        warehouseRepository.save(warehouse);
    }

    public void removeProduct(long warehouseId, Product product, long quantity) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId).orElse(null);

        WarehouseItem item = warehouse.getItems().stream().filter(x -> x.getProduct().getProductId()
                        == product.getProductId())
                .findFirst().orElse(null);

        if (item == null) {
            return;
        }

        if (item.getQuantity() - quantity <= 0) {
            warehouse.getItems().remove(item);
        }
        else {
            item.setQuantity(item.getQuantity() - quantity);
        }

        warehouseRepository.save(warehouse);
    }

    public boolean save(Warehouse warehouse) {
        try {
            warehouseRepository.save(warehouse);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Warehouse warehouse) {
        try {
            warehouseRepository.delete(warehouse);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
