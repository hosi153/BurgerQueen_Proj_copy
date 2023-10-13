package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.member.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDto {

    private long orderId;
    private long memberId;

    private int totalCount;
    private int TotalDiscountPrice;
    private int totalPrice;

    private Order.OrderStatus orderStatus;
    private List<OrderProductResponseDto> orderProductResponseDtos;

    public void setMember(Member member){
        this.memberId = member.getMemberId();
    }
}
