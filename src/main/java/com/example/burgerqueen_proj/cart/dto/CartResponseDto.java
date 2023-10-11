package com.example.burgerqueen_proj.cart.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartResponseDto {




    private long cartId;

    private long memberId;
    private List<CartProductResponseDto> cartProducts;


    private int totalCount;
    private int totalPrice;


    public void setMember(Member member){
        this.memberId=member.getMemberId();
    }

}
