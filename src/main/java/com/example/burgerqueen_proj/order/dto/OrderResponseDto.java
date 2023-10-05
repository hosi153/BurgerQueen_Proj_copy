package com.example.burgerqueen_proj.order.dto;

import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.user.entity.User;
import lombok.Data;

@Data
public class OrderResponseDto {

    private long orderId;
    private long userId;

    private Order.OrderStatus orderStatus;

    public void setUser(User user){
        this.userId = user.getUserId();
    }
}
