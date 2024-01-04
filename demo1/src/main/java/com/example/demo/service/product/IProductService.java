package com.example.demo.service.product;

import com.example.demo.model.dto.request.ProductRequest;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product saveOrUpdate(ProductRequest product);
    void delete(Long productId);
    Product findById(Long productId);
}
