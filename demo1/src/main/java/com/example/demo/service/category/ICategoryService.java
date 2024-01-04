package com.example.demo.service.category;

import com.example.demo.model.dto.response.CategoryResponse;
import com.example.demo.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    Page<CategoryResponse> findAll(Pageable pageable);
    Category saveOrUpdate(Category category);
    void delete(Long categoryId);
    Category findById(Long categoryId);
    Page<CategoryResponse> getPaginate(Pageable pageable);
    Page<Category> findAllPaginate(Pageable pageable);
    Page<CategoryResponse> searchByName(Pageable pageable,String name);
    Page<CategoryResponse> sortByName(Pageable pageable,String order,String sorts);
}
