package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.entity.OrderProduct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderPostDto {


    private long memberId;

    private Order.OrderStatus orderStatus = Order.OrderStatus.ORDER_REQUEST;

    private int totalCount;
    private int TotalDiscountPrice;
    private int totalPrice;

    private List<OrderProductDto> orderProductDtos;


    public Member getMember(){
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

}
