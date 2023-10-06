package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Data;

@Data
public class OrderResponseDto {

    private long orderId;
    private long userId;

    private Order.OrderStatus orderStatus;

    public void setMember(Member member){
        this.userId = member.getMemberId();
    }
}
