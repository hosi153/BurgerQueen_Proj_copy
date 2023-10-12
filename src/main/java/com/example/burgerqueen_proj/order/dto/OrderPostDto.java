package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Getter;

@Getter
public class OrderPostDto {


    private long userId;

    public Member getUser(){
        Member member = new Member();
        member.setMemberId(userId);
        return member;
    }

}
