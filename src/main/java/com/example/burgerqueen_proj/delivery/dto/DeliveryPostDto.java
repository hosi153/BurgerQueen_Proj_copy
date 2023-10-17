package com.example.burgerqueen_proj.delivery.dto;

import com.example.burgerqueen_proj.delivery.entity.Delivery;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class DeliveryPostDto {


    private long orderId;
    private Delivery.DeliveryStatus deliveryStatus;
    private LocalDateTime CreatedAt = LocalDateTime.now();

}
