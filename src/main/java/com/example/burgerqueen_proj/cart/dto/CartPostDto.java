package com.example.burgerqueen_proj.cart.dto;

import com.example.burgerqueen_proj.cart.entity.CartProduct;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;

import java.util.List;

@Getter

public class CartPostDto {


    private long memberId;

//    private long productId;

    private List<CartProduct> cartProducts;



    public Member getMember(){
        Member member = new Member();
        member.setMemberId(memberId);

        return member;
    }



}
