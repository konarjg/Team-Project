package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.entity.Warehouse;
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
