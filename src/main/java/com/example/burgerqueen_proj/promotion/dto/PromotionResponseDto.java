package com.example.burgerqueen_proj.promotion.dto;

import com.example.burgerqueen_proj.promotion.entity.Promotion;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PromotionResponseDto {
    private String promotionName;
    private String promotionDescription;
    //private Promotion.PromotionType promotionType;
    private String targetCategory;
    private Promotion.DiscountType discountType;
    private int amount;
    private Promotion.PromotionStatus promotionStatus;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    public PromotionResponseDto(Promotion promotion) {
        this.promotionName = promotion.getPromotionName();
        this.promotionDescription = promotion.getPromotionDescription();
        //this.promotionType = promotion.getPromotionType();
        this.targetCategory = promotion.getTargetCategory().getCategoryName();
        this.discountType = promotion.getDiscountType();
        this.amount = promotion.getAmount();
        this.promotionStatus = promotion.getPromotionStatus();
        this.startDate = promotion.getStartDate();
        this.endDate = promotion.getEndDate();
    }

    public static List<PromotionResponseDto> promotionResponseDtos(List<Promotion> promotions){
        List<PromotionResponseDto> dtos = new ArrayList<>(promotions.size());
        for(Promotion promotion : promotions){
            dtos.add(new PromotionResponseDto(promotion));
        }
        return dtos;
    }
}
