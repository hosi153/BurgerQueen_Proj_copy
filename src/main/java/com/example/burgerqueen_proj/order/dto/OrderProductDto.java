package com.example.burgerqueen_proj.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductDto {


    private int quantity;
//    private long orderId;
    private long productId;
}
