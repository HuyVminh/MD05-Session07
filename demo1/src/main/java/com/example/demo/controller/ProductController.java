package com.example.demo.controller;

import com.example.demo.model.dto.request.ProductRequest;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;
import com.example.demo.service.product.IProductService;
import com.example.demo.service.product.ProductServiceIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest product) {
        Product newProduct = productService.saveOrUpdate(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
