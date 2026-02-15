package com.contacthub.service;

import com.contacthub.domain.CategoryEntity;
import com.contacthub.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Optional<CategoryEntity> getCategoryById(UUID id) {
        return categoryRepository.findById(id);
    }
    
    public CategoryEntity createCategory(String name, String color) {
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        category.setColor(color);
        return categoryRepository.save(category);
    }
    
    public Optional<CategoryEntity> updateCategory(UUID id, String name, String color) {
        return categoryRepository.findById(id)
            .map(category -> {
                category.setName(name);
                category.setColor(color);
                return categoryRepository.save(category);
            });
    }
    
    public boolean deleteCategory(UUID id) {
        return categoryRepository.findById(id)
            .map(category -> {
                categoryRepository.delete(category);
                return true;
            })
            .orElse(false);
    }
}
