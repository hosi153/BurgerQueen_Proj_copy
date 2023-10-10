package com.example.burgerqueen_proj.product.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.product.entity.Product;
import com.example.burgerqueen_proj.promotion.entity.PromotionDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @Builder
@Getter
public class ProductPostDto {
    private String productName;
    private int productPrice;
    private int productCount;
    private String productImage;
    private String categoryName;


    public Category getCategory(){
        Category category = new Category();
        category.setCategoryName(this.categoryName);
        return category;
    }

    public Product toEntity(){
        return Product.builder()
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productImage(this.productImage)
                .productCount(this.productCount)
                .discountPrice(this.productPrice)
                .category(this.getCategory())
                .build();
    }
}
