package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Products p WHERE p.name LIKE CONCAT('%', :query, '%')")
    public List<Product> findAllByQuery(@Param("query") String query);
}
