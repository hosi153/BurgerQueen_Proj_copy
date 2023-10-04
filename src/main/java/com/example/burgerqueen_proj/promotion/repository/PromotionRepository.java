package com.example.burgerqueen_proj.promotion.repository;

import com.example.burgerqueen_proj.promotion.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
