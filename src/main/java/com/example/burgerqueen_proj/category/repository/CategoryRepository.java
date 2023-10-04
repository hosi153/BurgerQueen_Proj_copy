package com.example.burgerqueen_proj.category.repository;

import com.example.burgerqueen_proj.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
