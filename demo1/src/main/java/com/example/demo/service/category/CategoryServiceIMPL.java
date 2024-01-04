package com.example.demo.service.category;

import com.example.demo.model.dto.response.CategoryResponse;
import com.example.demo.model.entity.Category;
import com.example.demo.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceIMPL implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;
    @Override
    public Page<CategoryResponse> findAll(Pageable pageable) {
        Page<Category> categories = categoryRepository.findAll(pageable);
        return categories.map(category -> new CategoryResponse(category)); // lamda java8 : (CategoryResponse::new)
    }

    @Override
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public Category findById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElse(null);
    }

    @Override
    public Page<CategoryResponse> getPaginate(Pageable pageable) {
//        Page<CategoryResponse> listCategoryResponses = new Page<CategoryResponse>;
        Page<Category> categories = categoryRepository.findAll(pageable);
        for (Category category : categories) {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryName(category.getCategoryName());
            categoryResponse.setId(category.getId());
            categoryResponse.setStatus(category.getStatus());
            categoryResponse.setProducts(category.getProducts());
        }
        return null;
    }

    @Override
    public Page<Category> findAllPaginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }


    @Override
    public Page<CategoryResponse> searchByName(Pageable pageable, String name) {
        Page<Category> categoryPage = categoryRepository.findAllByCategoryNameContainingIgnoreCase(pageable,name);
        return categoryPage.map(CategoryResponse::new);
    }

    @Override
    public Page<CategoryResponse> sortByName(Pageable pageable, String order,String sorts) {
        Sort sort = Sort.by(sorts);
        if (order.equals("desc")) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Category> categoryPage = categoryRepository.findAll(pageRequest);
        return categoryPage.map(CategoryResponse::new);
    }

}
