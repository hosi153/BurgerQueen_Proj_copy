package com.example.burgerqueen_proj.delivery.mapper;

import com.example.burgerqueen_proj.delivery.dto.DeliveryPostDto;
import com.example.burgerqueen_proj.delivery.dto.DeliveryResponseDto;
import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.order.dto.OrderResponseDto;
import com.example.burgerqueen_proj.order.entity.Order;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "Spring")
public interface DeliveryMapper {

    default Delivery deliveryPostDtoToDelivery(DeliveryPostDto deliveryPostDto){
        Delivery delivery = new Delivery();
        Order order = new Order();
        order.setOrderId(deliveryPostDto.getOrderId());
        delivery.setOrder(order);

        return delivery;
    }
    default Delivery orderToDeliveryPostDto(Order order){

        Delivery delivery = new Delivery();
        delivery.setOrder(order);
        return delivery;

    }



    default DeliveryResponseDto deliveryToDeliveryResponseDto(Delivery delivery){
        DeliveryResponseDto deliveryResponseDto = new DeliveryResponseDto();
        deliveryResponseDto.setDeliveryId(delivery.getDeliveryId());
        deliveryResponseDto.setOrderId(delivery.getOrder().getOrderId());
        deliveryResponseDto.setDeliveryStatus(delivery.getDeliveryStatus());

        return deliveryResponseDto;
    }

    default List<DeliveryResponseDto> deliveryToDeliveryResponseDtos (List<Delivery> deliveries){

        List<DeliveryResponseDto> deliveryResponseDtos = new ArrayList<>(deliveries.size());
        for (Delivery delivery : deliveries ){
            deliveryResponseDtos.add(deliveryToDeliveryResponseDto(delivery));
        }

        return deliveryResponseDtos;
    }

}
