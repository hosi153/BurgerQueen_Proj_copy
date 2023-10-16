package com.example.burgerqueen_proj.order.controller;

import com.example.burgerqueen_proj.cart.dto.CartPatchDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.mapper.CartMapper;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.order.dto.OrderPatchDto;
import com.example.burgerqueen_proj.order.dto.OrderPostDto;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.mapper.OrderMapper;
import com.example.burgerqueen_proj.order.service.OrderService;
import com.example.burgerqueen_proj.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")

@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final MemberService memberService;
    private final CartService cartService;
    private final CartMapper cartMapper;
    @PostMapping
    public ResponseEntity postOrder(@RequestBody CartPatchDto cartPatchDto){

        System.out.println("aaaaaaaaaa");
        Cart cart = cartService.updateCart(cartMapper.cartPatchDtoToCart(cartPatchDto));

        Order order = orderService.creatOrder(orderMapper.orderPostDtoToOrder(orderMapper.cartToOrderPostDto(cart)));

//        System.out.println(userService.findUser(1L));

        System.out.println("email : " + order.getMember().getEmail());



        return new ResponseEntity<>(orderMapper.orderToOrderResponseDto(order), HttpStatus.CREATED);
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

    @PatchMapping("/{order-id}")
    public ResponseEntity patchOrder(@PathVariable("order-id")long orderId, @RequestBody OrderPatchDto orderPatchDto){
        return new ResponseEntity(HttpStatus.OK);
    }



}



