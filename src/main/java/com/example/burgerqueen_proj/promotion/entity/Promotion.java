package com.example.burgerqueen_proj.promotion.entity;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.entity.BasicEntity;
import com.example.burgerqueen_proj.exception.BusinessLogicException;
import com.example.burgerqueen_proj.exception.ExceptionCode;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private PromotionStatus promotionStatus =PromotionStatus.PROMOTION_STOP;

    @Builder.Default
    @OneToMany(mappedBy = "promotion")
    private List<PromotionDetails> promotionDetails = new ArrayList<>();

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public void makeStop(){
        this.promotionStatus = PromotionStatus.PROMOTION_STOP;
        targetCategory.setPromotion(null);
    }

    public void updatePromotion(Promotion promotion) {
        Optional.ofNullable(promotion.getPromotionName()).ifPresent(this::setPromotionName);
        //Optional.ofNullable(promotion.getTargetCategory()).ifPresent(this::setTargetCategory);
        if(promotion.getAmount() > 0) this.amount = promotion.getAmount();
        //Optional.ofNullable(promotion.getAmount()).ifPresent(this::setAmount);
        Optional.ofNullable(promotion.getPromotionStatus()).ifPresent(this::setPromotionStatus);
        Optional.ofNullable(promotion.getStartDate()).ifPresent(this::setStartDate);
        Optional.ofNullable(promotion.getEndDate()).ifPresent(this::setEndDate);

        //this.promotionType = promotion.getPromotionType();
//        this.discountType = promotion.getDiscountType();
//        this.amount = promotion.getAmount();
//        this.promotionStatus = promotion.getPromotionStatus();
//        this.startDate = promotion.getStartDate();
//        this.endDate = promotion.getEndDate();
        if(promotionStatus.equals(PromotionStatus.PROMOTION_ING)) mappingCategory();
    }

    private void mappingCategory() {
        if(targetCategory.getPromotion() == null) {
            targetCategory.setPromotion(this);
        }else if(targetCategory.getPromotion() !=this){
            throw new BusinessLogicException(ExceptionCode.CATEGORY_HAVE_ANOTHER_PROMOTION);
        }
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
    public double calculatePromotion(int originalPrice){
        double discountPrice;
        switch(this.discountType){
            case DISCOUNT_AMOUNT :
                discountPrice = originalPrice - amount;
                break;
            case DISCOUNT_RATE:
                //5000 * (100-20)/100
                discountPrice = originalPrice - ((double) (originalPrice * amount) /100);
                System.out.println(originalPrice+">>>>>"+amount+"==="+discountPrice);
                break;
            default:
                discountPrice = originalPrice;
                break;
        }

        return discountPrice;

    }

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

