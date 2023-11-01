package com.example.hellosprint.services;
import java.util.List;
import java.util.Optional;

import com.example.hellosprint.models.Category;
import com.example.hellosprint.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    public CategoryRepository categoryRepository;
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return  categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id){
        return categoryRepository.findById(id);
    }

    public Optional<Category> updateCategory(Long id, Category category){
        Optional<Category> searchCategory = categoryRepository.findById(id);
        if (searchCategory.isPresent()){
            Category oldCategory = searchCategory.get();
            oldCategory.setName(category.getName());
            oldCategory.setDescription(category.getDescription());
            return Optional.of(categoryRepository.save(oldCategory));
        }
        return Optional.empty();
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
