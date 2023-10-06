package com.example.burgerqueen_proj.promotion.entity;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.entity.BasicEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicInsert @DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Promotion extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long promotionId;

    private String promotionName;

//    @Builder.Default
//    @Enumerated(EnumType.STRING)
//    private PromotionType promotionType = PromotionType.PROMOTION_EACH;

    @OneToOne
    @JoinColumn(name="categoryId")
    private Category targetCategory;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private DiscountType discountType = DiscountType.DISCOUNT_AMOUNT;


    private int amount;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PromotionStatus promotionStatus =PromotionStatus.PROMOTION_ING;

    @Builder.Default
    @OneToMany(mappedBy = "promotion")
    private List<PromotionDetails> promotionDetails = new ArrayList<>();

    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public void makeStop(){
        this.promotionStatus = PromotionStatus.PROMOTION_STOP;
    }

    public void updatePromotion(Promotion promotion) {
        this.promotionName = promotion.getPromotionName();
        //this.promotionType = promotion.getPromotionType();
        this.discountType = promotion.getDiscountType();
        this.amount = promotion.getAmount();
        this.promotionStatus = promotion.getPromotionStatus();
        this.startDate = promotion.getStartDate();
        this.endDate = promotion.getEndDate();
    }

//    public enum PromotionType{
//        PROMOTION_SET(0, "세트상인할품 "),// 세트 상품 할인
//        PORMOTION_EVENT(1, "무료 증정"), //무료로 증정되는 사은품
//        PROMOTION_EACH(2, "단품 할인"), //단품 할인
//        PROMOTION_GRADE(3, "등급 할인"); //고객 등급별 할인
//
//        private int promotionTypeNumber;
//        private String promotionTypeName;
//
//        PromotionType(int promotionTypeNumber, String promotionTypeName){
//            this.promotionTypeNumber = promotionTypeNumber;
//            this.promotionTypeName = promotionTypeName;
//        }
//
//
//    }

    public enum DiscountType{
        DISCOUNT_AMOUNT(0, "정액할인"), // 정액 할인
        DISCOUNT_RATE(1,"정률할인"); // 정률 할인

        private int discountTypeNumber;
        private String discountTypeName;
        DiscountType(int discountTypeNumber,String discountTypeName){
            this.discountTypeNumber = discountTypeNumber;
            this.discountTypeName = discountTypeName;
        }
    }



    public enum PromotionStatus{
        PROMOTION_ING(0,"진행중"), // 프로모션 진행중
        PROMOTION_STOP(1, "종료"); // 프로모션 종료

        private int statusNumber;
        private String statusName;

        PromotionStatus(int statusNumber, String statusName) {
            this.statusNumber = statusNumber;
            this.statusName = statusName;
        }
    }



}

