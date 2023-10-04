package com.example.burgerqueen_proj.category.service;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
//        categoryRepository.save(Category.builder().categoryName("햄버거").build());
//        categoryRepository.save(Category.builder().categoryName("사이드").build());
    }


    public Category findCategoryById(long categoryId) {
        Optional<Category> optionalCategory =categoryRepository.findById(categoryId);
        Category category = optionalCategory.orElseThrow();
        return category;

    }

}
