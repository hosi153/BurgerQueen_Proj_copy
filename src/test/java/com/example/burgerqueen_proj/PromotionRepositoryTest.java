package com.example.burgerqueen_proj;

import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import com.example.burgerqueen_proj.promotion.entity.PromotionDetails;
import com.example.burgerqueen_proj.product.repository.ProductRepository;
import com.example.burgerqueen_proj.promotion.repository.PromotionRepository;
import com.example.burgerqueen_proj.promotion.repository.PromotionDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


@DataJpaTest
public class PromotionRepositoryTest {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PromotionDetailsRepository promotionDetailsRepository;

    @Autowired
    private ProductRepository productRepository;

    private Product product;
    private Product pooductNullPromotion;

//    @Autowired
//    private PromotionInfo promotionInfo;

    @BeforeEach
    public void makeData(){
        //1. product 가져오기
        product = productRepository.findById(1L).orElseThrow();
        pooductNullPromotion = productRepository.findById(2L).orElseThrow();

        //2. 프로모션 데이터 생성
        Promotion promotionInfo1 = Promotion.builder().amount(1000).promotionName("테스트 프로모션1").build();
        Promotion promotionInfo2 = Promotion.builder().amount(1000).promotionName("테스트 프로모션2").promotionStatus(Promotion.PromotionStatus.PROMOTION_STOP).build();

        Promotion savedPromotion1 = promotionRepository.save(promotionInfo1);
        Promotion savedPromotion = promotionRepository.save(promotionInfo2);

        //3. 프로모션 디테일 데이터 생성
        PromotionDetails promotionDetails1 = PromotionDetails.builder().promotion(promotionInfo1).product(product).build();
        PromotionDetails promotionDetails2 = PromotionDetails.builder().promotion(promotionInfo2).product(product).build();
        PromotionDetails savedDetails1 = promotionDetailsRepository.save(promotionDetails1);
        PromotionDetails savedDetails2 = promotionDetailsRepository.save(promotionDetails2);
        assertThat(savedDetails1.getPromotion(),is(equalTo(savedPromotion1)));

    }

    @DisplayName("프로모션 생성")
    @Test
    public void makePromotion(){
        //given : 프로모션 데이터 생성
        Promotion promotionInfo = Promotion.builder().amount(1000).promotionName("테스트 프로모션").build();

        //when
        Promotion savedPromotion = promotionRepository.save(promotionInfo);

        //then
        assertNotNull(savedPromotion);
        assertTrue(promotionInfo.getPromotionName().equals(savedPromotion.getPromotionName()));
        assertTrue(promotionInfo.getPromotionType()==(savedPromotion.getPromotionType()));

    }

    @DisplayName("프로모션 찾기")
    @Test
    public void findPromotionByProduct(){

        List<Promotion> findPromotions = promotionDetailsRepository.findPromotion(product, Promotion.PromotionStatus.PROMOTION_ING);
        List<Promotion> findEmptyPromotions = promotionDetailsRepository.findPromotion(pooductNullPromotion, Promotion.PromotionStatus.PROMOTION_ING);

        System.out.println(">>>>>>>>>>>>>>>>>>");

        findPromotions.stream()
                .forEach(s-> System.out.println(s.getPromotionName()));

        //assertNull(findEmptyPromotions);

    }

}
