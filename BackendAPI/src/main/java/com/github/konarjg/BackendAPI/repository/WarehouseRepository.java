package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
