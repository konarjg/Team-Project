package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.dto.ClientCategory;
import com.github.konarjg.BackendAPI.dto.ClientProduct;
import com.github.konarjg.BackendAPI.entity.Category;
import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.requestBody.CreateCategoryBody;
import com.github.konarjg.BackendAPI.service.CategoryService;
import com.github.konarjg.BackendAPI.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public CategoryController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientCategory>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<ClientCategory> clientCategories = categories.stream().map(c -> {
            ClientCategory category = new ClientCategory();
            category.setCategoryId(c.getCategoryId());
            category.setName(c.getName());
            category.setIcon(c.getIcon());
            category.setProducts(c.getProducts().stream().map(p -> {
                ClientProduct product = new ClientProduct();
                product.setName(p.getName());
                product.setImage(p.getImage());
                product.setPrice(p.getPrice());
                product.setStock(p.getStock());
                product.setProductId(p.getProductId());

                return product;
            }).collect(Collectors.toList()));

            return category;
        }).collect(Collectors.toList());

        if (categories.isEmpty()) {
            return new ResponseEntity<>(clientCategories, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(clientCategories, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateCategoryBody data) {
        try {
            List<Product> products = data.getProducts().stream().map(productService::findById).collect(Collectors.toList());
            Category category = new Category();
            category.setName(data.getName());
            category.setIcon(data.getIcon());
            category.setProducts(products);

            if (!categoryService.save(category)) {
                return new ResponseEntity<>(category, HttpStatus.CONFLICT);
            }

            return new ResponseEntity<>(category, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<?> delete(@PathVariable Long categoryId) {
        if (!categoryService.deleteByCategoryId(categoryId)) {
            return new ResponseEntity<>(categoryId, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(categoryId, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ClientCategory data) {
        Category category = categoryService.findByCategoryId(data.getCategoryId());

        if (category == null) {
            return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
        }

        category.setIcon(data.getIcon());
        category.setName(data.getName());
        category.setIcon(data.getIcon());
        category.setProducts(data.getProducts().stream().map(p -> {
            Product product = productService.findById(p.getProductId());
            product.setName(p.getName());
            product.setImage(p.getImage());
            product.setPrice(p.getPrice());
            product.setStock(p.getStock());
            product.setProductId(p.getProductId());

            productService.save(product);
            return product;
        }).collect(Collectors.toList()));

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
