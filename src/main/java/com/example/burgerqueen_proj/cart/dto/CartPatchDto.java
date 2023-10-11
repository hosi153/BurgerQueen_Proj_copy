package com.example.burgerqueen_proj.cart.dto;

import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;

import java.util.List;

@Getter
public class CartPatchDto {
    private long cartId;

    private long memberId;

    private List<CartProduct> cartProducts;


    public void setCartId(long cartId){
        this.cartId = cartId;
    }

    public Member getMember(){
        Member member = new Member();
        member.setMemberId(memberId);

        return member;
    }

}
