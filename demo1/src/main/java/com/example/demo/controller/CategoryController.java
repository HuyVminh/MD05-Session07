package com.example.demo.controller;

import com.example.demo.model.dto.response.CategoryResponse;
import com.example.demo.model.entity.Category;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

//    @GetMapping("/categories")
//    public ResponseEntity<List<CategoryResponse>> getCategories() {
//        List<CategoryResponse> categories = categoryService.findAll();
//        return new ResponseEntity<>(categories, HttpStatus.OK);
//    }

    @GetMapping("/api/v1/categories")
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = "5", name = "limit") int limit,
            @RequestParam(defaultValue = "0", name = "page") int currentPage
    ) {
        Pageable pageable = PageRequest.of(currentPage, limit);
        Page<CategoryResponse> categoryPage = categoryService.findAll(pageable);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category newCategory = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Category categoryUpdate = categoryService.findById(id);
        categoryUpdate.setCategoryName(category.getCategoryName());
        categoryUpdate.setStatus(category.getStatus());
        Category newCategory = categoryService.saveOrUpdate(category);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        if (categoryService.findById(id) != null) {
            categoryService.delete(id);
            return new ResponseEntity<>("DELETE SUCCESSFULLY", HttpStatus.OK);
        }
        return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/v1/cate")
    public ResponseEntity<?> sortCategory(@RequestParam(defaultValue = "5", name = "limit") int limit,
                                          @RequestParam(defaultValue = "0", name = "page") int currentPage,
                                          @RequestParam(defaultValue = "asc", name = "sorts") String sorts,
                                          @RequestParam(defaultValue = "id", name = "order") String order) {
        Pageable pageable = PageRequest.of(currentPage, limit);
        Page<CategoryResponse> categoryPage = categoryService.sortByName(pageable, sorts, order);
        return new ResponseEntity<>(categoryPage, HttpStatus.OK);
    }

    @GetMapping("api/v1/searchCate")
    public ResponseEntity<?> searchCategory(@RequestParam(defaultValue = "5", name = "limit") int limit,
                                            @RequestParam(defaultValue = "0", name = "page") int currentPage,
                                            @RequestParam(name = "keyword",required = false) String keyword,
                                            @RequestParam(defaultValue = "asc", name = "order") String sorts,
                                            @RequestParam(defaultValue = "id", name = "sorts") String order) {
        Pageable pageable;
        Page<CategoryResponse> categoryResponses;
        if (order.equals("desc")) {
            pageable = PageRequest.of(currentPage, limit, Sort.by(sorts).descending());
        } else {
            pageable = PageRequest.of(currentPage, limit, Sort.by(sorts).ascending());
        }
        if (keyword != null) {
            categoryResponses = categoryService.searchByName(pageable, keyword);
        } else {
            categoryResponses = categoryService.findAll(pageable);
        }
        return new ResponseEntity<>(categoryResponses, HttpStatus.OK);
    }
}
