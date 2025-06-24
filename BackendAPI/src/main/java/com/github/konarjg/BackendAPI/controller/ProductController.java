package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.entity.Category;
import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.requestBody.CreateProductBody;
import com.github.konarjg.BackendAPI.requestBody.UpdateProductBody;
import com.github.konarjg.BackendAPI.service.CategoryService;
import com.github.konarjg.BackendAPI.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<?> update(@PathVariable long productId, @RequestBody UpdateProductBody data) {
        Category category = categoryService.findByCategoryId(data.getCategoryId());

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productService.findById(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        product.setCategory(category);
        product.setName(data.getName());
        product.setImage("http://localhost:9090/images/" + data.getImage() + ".jpg");
        product.setStock(data.getStock());
        product.setPrice(data.getPrice());

        if (!productService.save(product)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateProductBody data) {
        Category category = categoryService.findByCategoryId(data.getCategoryId());

        if (category == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = new Product();
        product.setCategory(category);
        product.setName(data.getName());
        product.setImage("http://localhost:9090/images/" + data.getImage() + ".jpg");
        product.setStock(data.getStock());
        product.setPrice(data.getPrice());

        if (!productService.save(product)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> delete(@PathVariable Long productId) {
        if (!productService.deleteByProductId(productId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
