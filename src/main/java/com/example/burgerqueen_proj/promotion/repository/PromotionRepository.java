package com.example.burgerqueen_proj.promotion.repository;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    //List<Promotion> findAllByTargetCategory(Category category);
    List<Promotion> findAllByPromotionStatus(Promotion.PromotionStatus promotionStatus);
}
