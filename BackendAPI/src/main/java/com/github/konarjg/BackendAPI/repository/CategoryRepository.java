package com.github.konarjg.BackendAPI.repository;

import com.github.konarjg.BackendAPI.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
