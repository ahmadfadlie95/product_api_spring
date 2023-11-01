package com.example.hellosprint.controllers;

import com.example.hellosprint.models.Category;
import com.example.hellosprint.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    //Create
    @PostMapping
    public Category createCategory(@RequestBody Category category){return categoryService.createCategory(category);}



    //Read all
    @GetMapping
    public List<Category> getAllCategories (){return categoryService.getAllCategories();}


    //Read by ID
    @GetMapping("/{id}")
    public Optional<Category> getCategoryById (@PathVariable Long id){return categoryService.getCategoryById(id);}


    //Update
    @PutMapping("/{id}")
    public Optional<Category> updateCategory(@PathVariable Long id, @RequestBody Category category){return categoryService.updateCategory(id, category);}


    //Delete
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){categoryService.deleteCategory(id);}
}
