package com.example.demo.service.product;

import com.example.demo.model.dto.request.ProductRequest;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.Product;
import com.example.demo.repository.ICategoryRepository;
import com.example.demo.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceIMPL implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product saveOrUpdate(ProductRequest productRequest) {
        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        Optional<Category> category = categoryRepository.findById(productRequest.getCategoryId());
        product.setCategory(category.orElse(null));
        return productRepository.save(product);
    }

    @Override
    public void delete(Long productId) {

    }

    @Override
    public Product findById(Long productId) {
        return null;
    }
}
