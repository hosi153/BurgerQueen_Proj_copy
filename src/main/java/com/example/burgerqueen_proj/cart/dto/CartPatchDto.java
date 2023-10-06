package com.example.burgerqueen_proj.cart.dto;

import com.example.burgerqueen_proj.cart.entity.Cart;
import lombok.Getter;

@Getter
public class CartPatchDto {
    private long cartId;


    public void setCartId(long cartId){
        this.cartId = cartId;
    }
}
