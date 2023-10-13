package com.example.burgerqueen_proj.delivery.dto;

import com.example.burgerqueen_proj.delivery.entity.Delivery;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeliveryResponseDto {

    private long deliveryId;
    private long orderId;
    private Delivery.DeliveryStatus deliveryStatus;
}
