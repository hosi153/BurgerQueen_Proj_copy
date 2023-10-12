package com.example.burgerqueen_proj.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class CartProductResponseDto {

    private long productId;
    private  Integer quantity;
    private String productName;
    private Integer price;
}
