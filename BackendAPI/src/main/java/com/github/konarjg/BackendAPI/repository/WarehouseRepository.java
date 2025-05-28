package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.entity.Warehouse;
import com.github.konarjg.BackendAPI.entity.WarehouseItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query("SELECT new com.github.konarjg.BackendAPI.entity.WarehouseItem(i.product, SUM(i.quantity)) FROM WarehouseItems i GROUP BY i.product.name")
    public List<WarehouseItem> getFullStockList();
    @Query("SELECT new com.github.konarjg.BackendAPI.entity.WarehouseItem(i.product, SUM(i.quantity)) FROM WarehouseItems i WHERE i.product.name = :productName")
    public WarehouseItem getStock(@Param("productName") String productName);
}
