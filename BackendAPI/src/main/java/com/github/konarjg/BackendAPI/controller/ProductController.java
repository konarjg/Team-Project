package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.requestBody.ProductDeleteRequest;
import com.github.konarjg.BackendAPI.requestBody.ProductRequest;
import com.github.konarjg.BackendAPI.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/fetch")
    public ResponseEntity<List<Product>> fetch() {
        List<Product> products = productService.findAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/filter")
    public ResponseEntity<List<Product>> filter(@RequestParam String query) {
        List<Product> products = productService.findAllByQuery(query);

        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Product> create(@RequestBody ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        if (!productService.save(product)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public ResponseEntity<Product> delete(@RequestBody ProductDeleteRequest request) {
        Product product = productService.findById(request.getProductId());

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!productService.delete(product)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
