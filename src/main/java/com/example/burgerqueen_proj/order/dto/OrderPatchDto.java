package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.entity.Order;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderPatchDto {


    private long orderId;
    private Order.OrderStatus orderStatus;

}
