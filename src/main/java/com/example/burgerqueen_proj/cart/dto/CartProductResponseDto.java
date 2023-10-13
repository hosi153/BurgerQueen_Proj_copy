package com.example.burgerqueen_proj.cart.dto;

import com.example.burgerqueen_proj.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class CartProductResponseDto {

    private long productId;
    private Integer quantity;
    private String productName;
    private String productStatus;
    private int productCount;
    private Integer discountPrice;
    private Integer price;
}
