package com.example.test.controller;

import com.example.test.entity.Product;
import com.example.test.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add/list")
    public ResponseEntity<List<Product>> addProducts(@RequestBody List<Product> request) {
        List<Product> fetched = productService.saveAndFetchProducts(request);
        return ResponseEntity.ok(fetched);
    }
}
