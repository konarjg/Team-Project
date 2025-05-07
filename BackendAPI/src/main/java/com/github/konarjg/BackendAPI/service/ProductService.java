package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findAllByQuery(String query) {
        return productRepository.findAllByQuery(query);
    }

    public boolean save(Product product) {
        try {
            productRepository.save(product);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Product product) {
        try {
            productRepository.delete(product);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
