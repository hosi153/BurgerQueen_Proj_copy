package com.example.burgerqueen_proj.promotion.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.promotion.entity.Promotion;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Getter
public class PromotionPostDto {
    private String promotionName;
    private String targetCategory;
    //private Promotion.PromotionType promotionType;
    private Promotion.DiscountType discountType;
    private int amount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endDate;

    public Category getTargetCategory(){
        Category category = new Category();
        category.setCategoryName(this.targetCategory);
        return category;
    }

    public Promotion toEntity(){
        return Promotion.builder()
                .promotionName(this.promotionName)
               // .promotionType(this.promotionType)
                .targetCategory(this.getTargetCategory())
                .amount(this.amount)
                .discountType(this.discountType)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }

}
