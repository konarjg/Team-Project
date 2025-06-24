package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Category;
import com.github.konarjg.BackendAPI.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public boolean save(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean deleteByCategoryId(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public Category findByCategoryId(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
