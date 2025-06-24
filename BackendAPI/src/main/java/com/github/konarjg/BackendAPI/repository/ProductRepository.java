package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
