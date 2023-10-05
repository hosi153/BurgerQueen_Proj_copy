package com.example.burgerqueen_proj.order.mapper;

import com.example.burgerqueen_proj.order.dto.OrderPostDto;
import com.example.burgerqueen_proj.order.dto.OrderResponseDto;
import com.example.burgerqueen_proj.order.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper{

    Order orderPostDtoToOrder(OrderPostDto orderPostDto);
    OrderResponseDto orderToOrderResponseDto(Order order);
    List<OrderResponseDto> orderToOrderResponseDtos(List<Order> orders);
}
