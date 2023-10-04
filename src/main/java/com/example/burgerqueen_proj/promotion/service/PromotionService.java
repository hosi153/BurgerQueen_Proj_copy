package com.example.burgerqueen_proj.promotion.service;

import com.example.burgerqueen_proj.product.service.ProductService;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import com.example.burgerqueen_proj.promotion.repository.PromotionDetailsRepository;
import com.example.burgerqueen_proj.promotion.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final ProductService productService;
    private final PromotionRepository promotionRepository;
    private final PromotionDetailsRepository promotionDetailsRepository;







    //PROMOTION_SET
    //details에서 프로모션에 해당되는 상품들을 조회하여 주문서에 할인가격 적용


    //PROMOTION_EACH, PROMOTION_GRADE 계산
    //둘다 단품 혹은 장바구니전체 에 대해 정액/정률 할인으로 동일한 메서드로 처리 가능
    public int calPromotionEachAndGrade(int price, Promotion promotion){
        if(promotion.getDiscountType().equals(Promotion.DiscountType.DISCOUNT_AMOUNT)){
            //DiscountType : 정액할인
            return price -promotion.getAmount();
        }
        //DiscountType : 정률할인
        return price-(price*promotion.getAmount());
    }

    //TODO : PROMOTION_EVENT인 경우 cart에 사은품 추가


    //productId 기준, 활성화된 promotion만 조회
    public List<Promotion> getActivePromotionsByProductId(Long productId){
       Promotion.PromotionStatus activePromotion = Promotion.PromotionStatus.PROMOTION_ING;
       return promotionDetailsRepository.findPromotion(productService.findVerifyProduct(productId), activePromotion);
   }
}
