package com.example.burgerqueen_proj.order.controller;

import com.example.burgerqueen_proj.cart.dto.CartPatchDto;
import com.example.burgerqueen_proj.cart.entity.Cart;
import com.example.burgerqueen_proj.cart.mapper.CartMapper;
import com.example.burgerqueen_proj.cart.service.CartService;
import com.example.burgerqueen_proj.delivery.entity.Delivery;
import com.example.burgerqueen_proj.delivery.mapper.DeliveryMapper;
import com.example.burgerqueen_proj.delivery.service.DeliveryService;
import com.example.burgerqueen_proj.order.dto.OrderPatchDto;
import com.example.burgerqueen_proj.order.dto.OrderPostDto;
import com.example.burgerqueen_proj.order.entity.Order;
import com.example.burgerqueen_proj.order.mapper.OrderMapper;
import com.example.burgerqueen_proj.order.service.OrderService;
import com.example.burgerqueen_proj.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/order")

@RequiredArgsConstructor
@Transactional
//@Api(value = "OrderController" , tags = "주문 관리 API")

public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final MemberService memberService;
    private final CartService cartService;
    private final CartMapper cartMapper;
    private final DeliveryMapper deliveryMapper;
    private final DeliveryService deliveryService;

    @PostMapping
//    @ApiOperation(value = "주문 생성",notes = "장바구니 수정 + 주문 생성 + 배달 요청 ")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "cartId",value = "장바구니 ID"),
//
//            @ApiImplicitParam(name = "cartProductPatchDtos", value = "장바구니 상품 정보")}
//    )

    public ResponseEntity postOrder(@RequestBody CartPatchDto cartPatchDto){
        System.out.println("오더 생성 시작!");

        Cart cart = cartService.updateCart(cartMapper.cartPatchDtoToCart(cartPatchDto)); //장바구니 수정 후 수정된 장바구니 정보를 기준으로 주문 생성

        Order order = orderService.creatOrder(orderMapper.orderPostDtoToOrder(orderMapper.cartToOrderPostDto(cart))); // 수정된 장바구니 정보를 Mapper를 통해 Order로 변환 후 주문 생성
//        cartService.clearCartProduct(cart.getCartId()); // 기존 장바구니의 항목 초기화 ( 미구현 / 수정 필요 )
        deliveryService.createDelivery(deliveryMapper.orderToDeliveryPostDto(order)); //주문정보를 기준으로 배달 요청

        System.out.println("email : " + order.getMember().getEmail());


        System.out.println("오더 생성 끝!");

        return new ResponseEntity<>(orderMapper.orderToOrderResponseDto(order), HttpStatus.CREATED);
    }


    @GetMapping("/{order-id}")
    @ApiOperation(value = "주문 정보 단건 조회")
    @ApiImplicitParam
    public ResponseEntity getOrder(@PathVariable("order-id")long orderId){
        Order order = orderService.findOrder(orderId);
        return new ResponseEntity<>(orderMapper.orderToOrderResponseDto(order),HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "주문 정보 전체 조회")

    public ResponseEntity getOrders(@RequestParam int page, @RequestParam int size){
        Page<Order> pageOrder = orderService.findOrders(page -1 , size);
        List<Order> orders = pageOrder.getContent();

        return new ResponseEntity<>(orderMapper.orderToOrderResponseDtos(orders),HttpStatus.OK);
    }


    @DeleteMapping("/{order-id}")
    @ApiOperation(value = "주문 정보 삭제")

    public ResponseEntity deleteOrder(@PathVariable("order-id")long orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{order-id}")
    @ApiOperation(value = "주문 정보 수정")

    public ResponseEntity patchOrder(@PathVariable("order-id")long orderId, @RequestBody OrderPatchDto orderPatchDto){
        return new ResponseEntity(HttpStatus.OK);
    }



}



