package com.example.burgerqueen_proj.product.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import lombok.Builder;

public class ProductPostDto {

    private String productName;
    private int productPrice;
    private int productCount;
    private String productImage;

    private Category category;
    @Builder
    public ProductPostDto(String productName, int productPrice, int productCount, String productImage, Category category) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productImage = productImage;
        this.category = category;
    }
}
