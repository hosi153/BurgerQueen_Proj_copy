package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.user.entity.User;
import lombok.Getter;

@Getter
public class OrderPostDto {


    private long userId;
//
//    private Order.OrderStatus orderStatus;

    public User getUser(){
        User user = new User();
        user.setUserId(userId);
        return user;
    }

}
