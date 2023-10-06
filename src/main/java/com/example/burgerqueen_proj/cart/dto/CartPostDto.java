package com.example.burgerqueen_proj.cart.dto;

import com.example.burgerqueen_proj.cart.entity.CartDetail;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;

import java.util.List;

@Getter
public class CartPostDto {


    private long memberId;

    private List<CartDetail> cartDetails;

    public Member getMember(){
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }



}
