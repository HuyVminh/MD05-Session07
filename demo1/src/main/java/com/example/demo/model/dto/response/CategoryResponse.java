package com.example.demo.model.dto.response;

import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;

import java.util.List;

public class CategoryResponse {
    private Long id;
    private String categoryName;

    private Boolean status;

    private List<Product> products;

    public CategoryResponse() {
    }

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.categoryName = category.getCategoryName();
        this.status = category.getStatus();
        this.products = category.getProducts();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
