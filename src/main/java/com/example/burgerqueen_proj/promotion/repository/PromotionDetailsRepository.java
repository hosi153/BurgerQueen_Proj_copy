package com.example.burgerqueen_proj.promotion.repository;

import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import com.example.burgerqueen_proj.promotion.entity.PromotionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PromotionDetailsRepository extends JpaRepository<PromotionDetails, Long> {
    @Query("select pd.promotion from PromotionDetails pd where pd.product= :product and pd.promotion.promotionStatus= :status")
    List<Promotion> findPromotion(@Param("product") Product product, @Param("status") Promotion.PromotionStatus promotionStatus);


    //Optional<Promotion> findByProduct(Product product);
    //Optional<PromotionInfo> findAllByProductIdAndPromotionStatus(Long productId, PromotionInfo.PromotionStatus productStatus);

}
