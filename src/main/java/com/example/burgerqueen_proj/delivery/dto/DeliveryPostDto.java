package com.example.burgerqueen_proj.delivery.dto;

import com.example.burgerqueen_proj.delivery.entity.Delivery;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DeliveryPostDto {


    private long orderId;
    private Delivery.DeliveryStatus deliveryStatus;
    private LocalDateTime CreatedAt = LocalDateTime.now();

}
