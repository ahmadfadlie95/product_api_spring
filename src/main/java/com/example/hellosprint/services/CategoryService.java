package com.example.hellosprint.services;

import com.example.hellosprint.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category createCategory(Category category);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Optional<Category> updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
