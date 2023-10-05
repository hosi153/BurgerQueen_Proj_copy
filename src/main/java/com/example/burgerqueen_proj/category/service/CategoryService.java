package com.example.burgerqueen_proj.category.service;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.category.repository.CategoryRepository;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Category category = optionalCategory.orElseThrow(()-> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
        return category;

    }

    public Category findVerifiedCategoryByName(String categoryName){
        Optional<Category> optionalCategory = findCategoryByName(categoryName);
        return optionalCategory.orElseThrow(()-> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
    }
    public Optional<Category> findCategoryByName(String categoryName){
        return categoryRepository.findByCategoryName(categoryName);
    }

    public List<Category> findAllCategory(){
        return categoryRepository.findAll();
    }

    public void deleteCategory(long categoryId) {
        findCategoryById(categoryId);
        categoryRepository.deleteById(categoryId);
    }


    public Category createCategory(Category category) {
        Optional<Category> existCategory = findCategoryByName(category.getCategoryName());
        if(existCategory.isPresent())
            throw new BusinessLogicException(ExceptionCode.CATEGORY_DUPLICATED);
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category) {
        Category updatedCategory = findCategoryById(category.getCategoryId());
        updatedCategory.updateCategory(category);
        return categoryRepository.save(updatedCategory);
    }
}
