package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.entity.Location;
import com.github.konarjg.BackendAPI.entity.Product;
import com.github.konarjg.BackendAPI.entity.Warehouse;
import com.github.konarjg.BackendAPI.entity.WarehouseItem;
import com.github.konarjg.BackendAPI.requestBody.WarehouseAddRemoveProductRequest;
import com.github.konarjg.BackendAPI.requestBody.WarehouseDeleteRequest;
import com.github.konarjg.BackendAPI.requestBody.WarehouseRequest;
import com.github.konarjg.BackendAPI.service.LocationService;
import com.github.konarjg.BackendAPI.service.ProductService;
import com.github.konarjg.BackendAPI.service.WarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final LocationService locationService;
    private final ProductService productService;

    public WarehouseController(WarehouseService warehouseService, LocationService locationService, ProductService productService) {
        this.warehouseService = warehouseService;
        this.locationService = locationService;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/fetch")
    public ResponseEntity<List<Warehouse>> fetch() {
        List<Warehouse> warehouses = warehouseService.findAll();

        if (warehouses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Warehouse> create(@RequestBody WarehouseRequest request) {
        Location location = locationService.findById(request.getLocationId());

        Warehouse warehouse = new Warehouse();
        warehouse.setName(request.getName());
        warehouse.setLocation(location);
        warehouse.setItems(new ArrayList<>());

        if (!warehouseService.save(warehouse)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(warehouse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public ResponseEntity<Warehouse> delete(@RequestBody WarehouseDeleteRequest request) {
        Warehouse warehouse = warehouseService.findById(request.getWarehouseId());

        if (warehouse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!warehouseService.delete(warehouse)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(warehouse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/full-stock")
    public ResponseEntity<List<WarehouseItem>> getFullStock() {
        return new ResponseEntity<>(warehouseService.getFullStockList(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "stock")
    public ResponseEntity<WarehouseItem> getStock(@RequestParam String productName) {
        return new ResponseEntity<>(warehouseService.getStock(productName), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/add-product")
    public ResponseEntity<Void> addProduct(@RequestBody WarehouseAddRemoveProductRequest request) {
        Product product = productService.findById(request.getProductId());

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        warehouseService.addProduct(request.getWarehouseId(), product, request.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/remove-product")
    public ResponseEntity<Void> removeProduct(@RequestParam WarehouseAddRemoveProductRequest request) {
        Product product = productService.findById(request.getProductId());

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        warehouseService.removeProduct(request.getWarehouseId(), product, request.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
