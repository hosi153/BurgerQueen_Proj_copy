package com.example.burgerqueen_proj.order.mapper;

import com.example.burgerqueen_proj.member.entity.Member;
import com.example.burgerqueen_proj.order.dto.OrderPatchDto;
import com.example.burgerqueen_proj.order.dto.OrderPostDto;
import com.example.burgerqueen_proj.order.dto.OrderProductResponseDto;
import com.example.burgerqueen_proj.order.dto.OrderResponseDto;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.entity.OrderProduct;
import com.example.burgerqueen_proj.product.entity.Product;
import org.aspectj.weaver.ast.Or;
import org.mapstruct.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper{

//    Order orderPostDtoToOrder(OrderPostDto orderPostDto);

    default Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto){
        Order order = new Order();
        order.setOrderId(orderPatchDto.getOrderId());
        order.setOrderStatus(orderPatchDto.getOrderStatus());



        return order;
    }


    default Order orderPostDtoToOrder(OrderPostDto orderPostDto){
        Order order = new Order();
        Member member = new Member();
        member.setMemberId(orderPostDto.getMemberId());

        List<OrderProduct> orderProducts = orderPostDto.getOrderProductDtos().stream()
                .map(orderProductDto -> {
                    OrderProduct orderProduct = new OrderProduct();
                    Product product = new Product();
                    product.setProductId(orderProductDto.getProductId());
                    orderProduct.addOrder(order);
                    orderProduct.addProduct(product);
                    orderProduct.setQuantity(orderProductDto.getQuantity());

                    return orderProduct;

                }).collect(Collectors.toList());

        order.setMember(member);
        order.setOrderProducts(orderProducts);

        order.setTotalDiscountPrice(orderPostDto.getTotalDiscountPrice());



        order.getOrderProducts().stream()
                .forEach(cartProduct -> order.setTotalCount(order.getTotalCount()+ cartProduct.getQuantity()));









        return order;
    }







    default OrderResponseDto orderToOrderResponseDto(Order order){
        List<OrderProduct> orderProducts = order.getOrderProducts();
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMemberId(order.getMember().getMemberId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setTotalDiscountPrice(order.getTotalDiscountPrice());
        orderResponseDto.setTotalCount(order.getTotalCount());
        orderResponseDto.setTotalPrice(order.getTotalPrice());
        orderResponseDto.setOrderProductResponseDtos(orderProductToOrderProductResponseDto(orderProducts));
        return orderResponseDto;

    }
    List<OrderResponseDto> orderToOrderResponseDtos(List<Order> orders);


    default List<OrderProductResponseDto> orderProductToOrderProductResponseDto(List<OrderProduct> orderProducts){
        return orderProducts
                .stream()
                .map(orderProduct -> OrderProductResponseDto
                        .builder()
                        .orderProductId(orderProduct.getOrderProductId())
                        .productId(orderProduct.getProduct().getProductId())
                        .productName(orderProduct.getProduct().getProductName())
                        .price(orderProduct.getProduct().getProductPrice())
                        .quantity(orderProduct.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }



}
