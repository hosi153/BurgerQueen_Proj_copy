package com.example.burgerqueen_proj.order.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderProductResponseDto {

    private long orderProductId;
    private long productId;
    private  Integer quantity;
    private String productName;
    private Integer price;
}
