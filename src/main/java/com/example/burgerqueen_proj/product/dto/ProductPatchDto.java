package com.example.burgerqueen_proj.product.dto;

import com.example.burgerqueen_proj.category.entity.Category;
import com.example.burgerqueen_proj.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class ProductPatchDto {
    @Setter
    private long productId;
    private String productName;
    private int productPrice;
    private int productCount;
    private String productImage;
    private Long editUserId;
    private String categoryName;
    private Product.ProductStatus status;

    public Category getCategory(){
        Category category = new Category();
        category.setCategoryName(this.categoryName);
        return category;
    }

    public Product toEntity(){
        return Product.builder()
                .productId(this.productId)
                .productName(this.productName)
                .productPrice(this.productPrice)
                .productImage(this.productImage)
                .productCount(this.productCount)
                .category(this.getCategory())
                .productStatus(this.status)
                .build();
    }
}