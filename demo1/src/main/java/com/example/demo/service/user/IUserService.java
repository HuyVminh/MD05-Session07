package com.example.demo.service.user;

import com.example.demo.model.dto.response.CategoryResponse;
import com.example.demo.model.entity.Category;
import com.example.demo.model.entity.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User saveOrUpdate(User user);
    void delete(Long userId);
    User findById(Long userId);
}
