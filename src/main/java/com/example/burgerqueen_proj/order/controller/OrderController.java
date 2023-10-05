package com.example.burgerqueen_proj.order.controller;

import com.example.burgerqueen_proj.order.dto.OrderPostDto;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.mapper.OrderMapper;
import com.example.burgerqueen_proj.order.service.OrderService;
import com.example.burgerqueen_proj.user.entity.User;
import com.example.burgerqueen_proj.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Validated
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserService userService;
    @PostMapping
    public ResponseEntity postOrder(@RequestBody OrderPostDto orderPostDto){
        Order order = orderService.creatOrder(orderMapper.orderPostDtoToOrder(orderPostDto));

//        System.out.println(userService.findUser(1L));

        System.out.println("email : " + order.getUser().getEmail());


        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }


    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id")long orderId){
        Order order = orderService.findOrder(orderId);
        return new ResponseEntity<>(orderMapper.orderToOrderResponseDto(order),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam int page, @RequestParam int size){
        Page<Order> pageOrder = orderService.findOrders(page -1 , size);
        List<Order> orders = pageOrder.getContent();

        return new ResponseEntity<>(orderMapper.orderToOrderResponseDtos(orders),HttpStatus.OK);
    }


    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable("order-id")long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
