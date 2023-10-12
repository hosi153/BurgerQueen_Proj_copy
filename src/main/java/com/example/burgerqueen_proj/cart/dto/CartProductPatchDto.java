package com.example.burgerqueen_proj.cart.dto;

import lombok.Getter;

@Getter
public class CartProductPatchDto {

    private long productId;

    private  int quantity;

    private long cartProductId;
}
